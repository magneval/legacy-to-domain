package io.saagie.ddd.legacytodomain.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UserRegistrationDto {
    @JsonProperty
    private String firstname;

    @JsonProperty
    private String lastname;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private Integer defaultPlatform;

    @JsonProperty
    private Map<Integer, String> platforms;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDefaultPlatform() {
        return defaultPlatform;
    }

    public void setDefaultPlatform(Integer defaultPlatform) {
        this.defaultPlatform = defaultPlatform;
    }

    public Map<Integer, String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Map<Integer, String> platforms) {
        this.platforms = platforms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
