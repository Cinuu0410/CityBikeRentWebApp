package pl.uwb.f4group.citybikerent.Enum;

import lombok.Data;


public enum UserRole {
    USER("User"),
    ADMIN("Admin"),
    ;
    private final String roleName;
    UserRole(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }

}
