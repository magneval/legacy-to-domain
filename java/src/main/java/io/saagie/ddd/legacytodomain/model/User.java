package io.saagie.ddd.legacytodomain.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Map<Integer, Role> roles;
    private Platform defaultPlatform;

    public User(String firstname, String lastname, String email, Platform defaultPlatform) {
        this(firstname, lastname, email, defaultPlatform, new HashMap<>());
    }

    public User(String firstname, String lastname, String email, Platform defaultPlatform, Map<Integer, Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.defaultPlatform = defaultPlatform;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Map<Integer, Role> getRoles() {
        return roles;
    }

    public Role getRole(Integer platformId) {
        return this.roles.get(platformId);
    }

    public void addRole(Integer platformId, Role role) {
        this.roles.put(platformId, role);
    }

    public Platform getDefaultPlatform() {
        return defaultPlatform;
    }

    public void setDefaultPlatform(Platform defaultPlatform) {
        this.defaultPlatform = defaultPlatform;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", defaultPlatform=" + defaultPlatform +
                '}';
    }
}
