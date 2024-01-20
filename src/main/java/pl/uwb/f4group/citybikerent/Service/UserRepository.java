package pl.uwb.f4group.citybikerent.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uwb.f4group.citybikerent.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}