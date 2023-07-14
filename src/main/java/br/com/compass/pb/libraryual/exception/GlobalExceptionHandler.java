package br.com.compass.pb.libraryual.exception;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occurred.");
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String resourceName = ex.getResourceName();
        String message = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setTitle(resourceName + " not found");
        return ResponseEntity.status(status).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String field = error.getField();
            String defaultMessage = error.getDefaultMessage();
            String errorMessage = "The field '" + field + "' " + defaultMessage;
            errorMessages.add(errorMessage);
        }

        if (!errorMessages.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
            ErrorResponse400 errorResponse400 = new ErrorResponse400(status.value(), status.toString(), errorMessages);
            return ResponseEntity.status(status).body(errorResponse400);
        }
        ErrorResponse400 errorResponse400 = new ErrorResponse400(status.value(), status.toString(), Collections.singletonList("Validation failed"));
        return ResponseEntity.status(status).body(errorResponse400);
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String resourceName = ex.getResourceName();
        String message = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), resourceName + " not found");
        return ResponseEntity.status(status).body(errorResponse);
    }*/

}
