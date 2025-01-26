package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeception extends RuntimeException{
    public ResourceNotFoundExeception(String resourceName, String fieldName, String valueName) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, valueName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.valueName = valueName;
    }
    private String resourceName;
    private String fieldName;
    private String valueName;
}
