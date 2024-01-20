package pl.uwb.f4group.citybikerent.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.uwb.f4group.citybikerent.Service.User;
import pl.uwb.f4group.citybikerent.Service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        if (userService.authenticate(username, password)) {
            User loggedInUser = userService.getUserByUsername(username);
            // Zapisz informacje o zalogowanym użytkowniku w sesji
            session.setAttribute("loggedInUser", loggedInUser);

            redirectAttributes.addFlashAttribute("successMessage", "Zalogowano pomyślnie");
            return "redirect:/main_page_citybikerent";  // Przekierowanie na stronę główną
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania");
            return "redirect:/login";  // Przekierowanie z powrotem do formularza logowania
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Pobierz atrybut sesji loggedInUser
        Object loggedInUser = session.getAttribute("loggedInUser");

        // Jeżeli użytkownik jest zalogowany, wykonaj operacje wylogowywania
        if (loggedInUser != null) {
            // Tutaj możesz dodać inne operacje związane z wylogowywaniem
            // na przykład: zakończenie sesji, czyszczenie atrybutów sesji itp.

            // Usuń atrybut sesji zalogowanego użytkownika
            session.removeAttribute("loggedInUser");
        }

        // Przekieruj użytkownika na stronę główną lub inną stronę po wylogowaniu
        return "redirect:/main_page_citybikerent";
    }
}
