package br.com.compass.pb.libraryual.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String field;

    public ErrorResponse(int value, String message) {
    }
}
