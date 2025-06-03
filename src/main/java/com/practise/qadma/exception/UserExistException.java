package com.practise.qadma.exception;

public class UserExistException extends Throwable {

    public UserExistException(String username) {
        super(username);
    }
}
