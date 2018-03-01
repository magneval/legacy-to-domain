package io.saagie.ddd.legacytodomain.model;

public enum Role {
    ADMIN("admin"),
    READER("reader"),
    WRITER("writer");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Role fromLabel(String label) {
        for (Role role : Role.values()) {
            if (role.label.equalsIgnoreCase(label)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Role{" +
                "label='" + label + '\'' +
                '}';
    }
}
