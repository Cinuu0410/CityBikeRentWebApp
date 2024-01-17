package pl.uwb.f4group.citybikerent.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.uwb.f4group.citybikerent.Service.UserService;
import pl.uwb.f4group.citybikerent.Service.LoginRequest;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        if (userService.authenticate(username, password)) {
            redirectAttributes.addFlashAttribute("successMessage", "Zalogowano pomyślnie");
            return "redirect:/main_page_citybikerent";  // Przekierowanie na stronę główną
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Błąd logowania");
            return "redirect:/login";  // Przekierowanie z powrotem do formularza logowania
        }
    }
}
