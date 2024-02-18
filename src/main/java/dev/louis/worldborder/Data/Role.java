package dev.louis.worldborder.Data;

public enum Role {
    ADMIN("worldborder.admin"),
    MOD("worldborder.mod"),
    MEMBER("worldborder.member"),
    GUEST("worldborder.guest");

    private String permission;
    Role(String permission) {
        this.permission = permission;
    }
}
