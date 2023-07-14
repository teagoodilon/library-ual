package br.com.compass.pb.libraryual.exception;

public class DataIntegrityViolationException extends RuntimeException {
    private final String resourceName;

    public DataIntegrityViolationException(String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
