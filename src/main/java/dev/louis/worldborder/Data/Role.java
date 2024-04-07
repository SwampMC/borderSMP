package dev.louis.worldborder.Data;

public enum Role {
    ADMIN("worldborder.role.admin"),
    MOD("worldborder.role.mod"),
    MEMBER("worldborder.role.member"),
    GUEST("worldborder.role.guest");

    private String permission;
    Role(String permission) {
        this.permission = permission;
    }

}
