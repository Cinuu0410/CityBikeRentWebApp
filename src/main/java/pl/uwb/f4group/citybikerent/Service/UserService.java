package pl.uwb.f4group.citybikerent.Service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uwb.f4group.citybikerent.Repository.UserRepository;
import pl.uwb.f4group.citybikerent.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        // Pobierz użytkownika z bazy danych na podstawie nazwy użytkownika
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // Haszuj podane hasło i porównaj z hasłem w bazie danych
            String hashedPassword = hashPassword(password);
            return hashedPassword.equals(user.getPassword());
        }

        return false;
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
    private String hashPassword(String password) {
        if (password != null) {
            // Wybierz algorytm haszujący (SHA-256 w tym przypadku)
            return DigestUtils.sha256Hex(password);
        } else {
            // Obsłuż sytuację, gdy hasło jest null
            throw new IllegalArgumentException("Password cannot be null");
        }
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(String username, String password, String firstName, String lastName, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        String hashedPassword = hashPassword(password);
        newUser.setPassword(hashedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        // Dodaj inne pola użytkownika, np. imię, nazwisko, e-mail
        userRepository.save(newUser);
    }

    public void saveBikeNumberToUser(User user, Long bikeNumber) {
        // Ustaw numer roweru dla użytkownika
        user.setCurrentRentalNumber(bikeNumber);
        // Zapisz zmiany w bazie danych
        userRepository.save(user);
    }
}
