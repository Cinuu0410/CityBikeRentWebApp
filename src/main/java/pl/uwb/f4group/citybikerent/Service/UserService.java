package pl.uwb.f4group.citybikerent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uwb.f4group.citybikerent.Service.User;
import pl.uwb.f4group.citybikerent.Service.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        User user    = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

}
