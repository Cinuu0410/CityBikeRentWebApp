package pl.uwb.f4group.citybikerent.Enum;

import lombok.Data;


public enum UserRole {
    USER("USER"),
    SUPER_USER("SUPER_USER"),
    SERVICE_TECHNICIAN("SERVICE"),
    ;
    private final String roleName;
    UserRole(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }

}
