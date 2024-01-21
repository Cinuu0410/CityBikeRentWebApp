package pl.uwb.f4group.citybikerent.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.uwb.f4group.citybikerent.Enum.UserRole;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    private String Id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Long currentRentalNumber;

    // Dodano pole wallet do encji User
    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
    private BigDecimal wallet;

    public User() {
        this.role = String.valueOf(UserRole.USER);
        this.wallet = BigDecimal.ZERO;
    }

    @Transient
    private String rawPassword;
}