package br.com.devdojo.endpoint;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import br.com.devdojo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity listAll(Pageable pageable) {
        return new ResponseEntity<>(studentRepository.findAll(pageable), HttpStatus.OK);
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
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @PostMapping("/list")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity saveList(@Valid @RequestBody List<Student> students) {
        return new ResponseEntity<>(studentRepository.saveAll(students), HttpStatus.CREATED);
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
