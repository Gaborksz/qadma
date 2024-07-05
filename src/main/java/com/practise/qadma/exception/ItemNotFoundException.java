package com.practise.qadma.exception;


public class ItemNotFoundException extends RuntimeException {

    private final String errorMessage;


    public ItemNotFoundException(long id, String className) {

        this.errorMessage = className + " with id: " + id + " not exists ";
    }

    public String getCustomErrorMessage() {
        return errorMessage;
    }
}
