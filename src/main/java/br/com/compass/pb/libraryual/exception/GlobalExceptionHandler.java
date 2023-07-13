package br.com.compass.pb.libraryual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
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

    @ExceptionHandler(BlankFieldException.class)
    public ResponseEntity<String> handleBlankFieldException(BlankFieldException ex) {
        String fieldName = ex.getFieldName();
        String message = "The field '" + fieldName + "' cannot be blank.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(message);
    }

}
