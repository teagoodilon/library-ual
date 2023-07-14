package br.com.compass.pb.libraryual.exception;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class ForeignKeyViolationException extends RuntimeException {
    private List<String> affectedFields;

    public ForeignKeyViolationException(String message, List<String> affectedFields, DataIntegrityViolationException ex) {
        super(message);
        this.affectedFields = affectedFields;
    }

    public List<String> getAffectedFields() {
        return affectedFields;
    }
}