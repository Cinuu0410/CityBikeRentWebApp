package pl.uwb.f4group.citybikerent.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.uwb.f4group.citybikerent.Enum.UserRole;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Long currentRentalNumber;
    public User() {
        this.role = String.valueOf(UserRole.USER); // Ustaw domyślną wartość "user" w konstruktorze
    }
    @Transient
    private String rawPassword;
}