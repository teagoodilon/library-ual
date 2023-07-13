package br.com.compass.pb.libraryual.exception;

import lombok.Data;

@Data
public class ErrorReponseConstraint {
    private int status;
    private String error;
    private String message;
    private String detail;
    private String path;

    public ErrorReponseConstraint(int status, String error, String message, String detail, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.detail = detail;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
