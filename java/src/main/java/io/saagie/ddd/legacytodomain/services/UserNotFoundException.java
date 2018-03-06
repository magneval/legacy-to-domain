package io.saagie.ddd.legacytodomain.services;

public class UserNotFoundException extends Exception {
    private Integer id;

    public UserNotFoundException(Integer id) {
        this.id = id;
    }
}
