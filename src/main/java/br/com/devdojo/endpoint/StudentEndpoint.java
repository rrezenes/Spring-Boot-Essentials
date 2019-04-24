package br.com.devdojo.endpoint;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import br.com.devdojo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getStudentById(@PathVariable("id") Long id) {

        this.verifyIfStudentExists(id);
        Optional<Student> student = studentRepository.findById(id);

        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity findStudentsByName(@PathVariable("name") String name) {
        return new ResponseEntity(studentRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.verifyIfStudentExists(id);
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Student student) {
        this.verifyIfStudentExists(student.getId());
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id) {
        if (!studentRepository.findById(id).isPresent())
            throw new ResourceNotFoundException("Student not found for ID: " + id);
    }

}
