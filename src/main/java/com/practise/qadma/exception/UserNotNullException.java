package com.practise.qadma.exception;

public class UserNotNullException extends RuntimeException {

    public UserNotNullException() {
        super("id cannot be set, use setUser to set id to ensure fields always match");
    }
}
