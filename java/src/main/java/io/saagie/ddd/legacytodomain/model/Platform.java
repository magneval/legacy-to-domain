package io.saagie.ddd.legacytodomain.model;

import java.util.HashMap;
import java.util.Map;

public class Platform {
    private Integer id;
    private String name;
    private Map<Integer, Role> roles = new HashMap<>();

    public Platform(Integer id, String name) {
        this(id, name, new HashMap<>());
    }

    public Platform(Integer id, String name, Map<Integer, Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Role> getRoles() {
        return roles;
    }

    public Role getRole(Integer userId) {
        return this.roles.get(userId);
    }

    public void addRole(Integer userId, Role role) {
        this.roles.put(userId, role);
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
