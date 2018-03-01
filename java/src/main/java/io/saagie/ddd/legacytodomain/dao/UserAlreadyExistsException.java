package io.saagie.ddd.legacytodomain.dao;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String email) {
    }
}
