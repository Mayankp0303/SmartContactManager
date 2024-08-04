package com.scm.contactmanager.helper;

public class ResourceNotFoundExcepton extends RuntimeException {

    public ResourceNotFoundExcepton(){
        super("Resource Not Found ");
    }
    public ResourceNotFoundExcepton(String message){
        super(message);
    }
}
