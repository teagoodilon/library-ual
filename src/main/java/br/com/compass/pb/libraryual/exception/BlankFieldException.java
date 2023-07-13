package br.com.compass.pb.libraryual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BlankFieldException extends RuntimeException {
    private String fieldName;

    public BlankFieldException(String fieldName) {
        super("White field: " + fieldName);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ProblemDetail toProblemDetail() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "White field: " + fieldName;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, errorMessage);
        problemDetail.setTitle("Bad Request");
        return problemDetail;
    }

}




