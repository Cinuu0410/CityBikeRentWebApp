package pl.uwb.f4group.citybikerent.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uwb.f4group.citybikerent.Service.User;

@Controller
@Slf4j
public class BikeRentController {

    @GetMapping("/main_page_citybikerent")
    public String mainPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "main_page_citybikerent";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }


    @GetMapping("/offer")
    public String offerPage(Model model, HttpSession session) {
        // Sprawdź, czy istnieje atrybut sesji dla zalogowanego użytkownika
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }

        return "offer_page";
    }

    @GetMapping("/price_list")
    public String priceListPage(Model model, HttpSession session){
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "price_list_page";
    }

    @GetMapping("/contact")
    public String contactPage(Model model, HttpSession session){
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "contact_page";
    }

    @GetMapping("/rent")
    public String rentPage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            model.addAttribute("loggedInUser", loggedInUser);
        }

        return "rent_page";
    }


    @GetMapping("/regulations")
    public String regulationsPage(Model model, HttpSession session){
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "regulations_page";
    }

    @GetMapping("/about_us")
    public String aboutUsPage(Model model, HttpSession session){
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "about_us_page";
    }

}


