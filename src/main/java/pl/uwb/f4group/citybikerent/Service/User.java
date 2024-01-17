package pl.uwb.f4group.citybikerent.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

}