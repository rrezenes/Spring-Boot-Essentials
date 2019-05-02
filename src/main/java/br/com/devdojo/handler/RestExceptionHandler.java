package br.com.devdojo.handler;

import br.com.devdojo.error.ErrorDetails;
import br.com.devdojo.error.ResourceNotFoundDetails;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e) {
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(e.getMessage())
                .developerMessage(e.getClass().getName())
                .build();

        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails resourceNotFoundDetails = ValidationErrorDetails.ValidationErrorDetailsBuilder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation error")
                .details("Field validation error")
                .developerMessage(e.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessage)
                .build();

        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.newBuilder()
                .withTimestamp(new Date().getTime())
                .withStatus(status.value())
                .withTitle("Internal Exception")
                .withDetails(e.getMessage())
                .withDeveloperMessage(e.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, status);
    }
}
