package com.WeekXII.challenger3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdValueOutOfBoundException extends RuntimeException{
    private String resourceName;
    private long fieldValue;

    public IdValueOutOfBoundException(String resourceName, long fieldValue) {
        super(String.format("%s %s value is out of the bound", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
