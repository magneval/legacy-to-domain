package io.saagie.ddd.legacytodomain.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlatformRoleDto {
    @JsonProperty
    private String role;

    @JsonProperty
    private Integer platformId;

    public Integer getPlatformId() {
        return platformId;
    }

    public String getRole() {
        return role;
    }

    public PlatformRoleDto setRole(String role) {
        this.role = role;
        return this;
    }

    public PlatformRoleDto setPlatformId(Integer platformId) {
        this.platformId = platformId;
        return this;
    }
}
