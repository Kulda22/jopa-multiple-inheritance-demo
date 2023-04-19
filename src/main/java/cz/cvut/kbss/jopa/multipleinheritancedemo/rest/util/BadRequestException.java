package cz.cvut.kbss.jopa.multipleinheritancedemo.rest.util;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
