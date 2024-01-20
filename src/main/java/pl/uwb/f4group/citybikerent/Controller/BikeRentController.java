package pl.uwb.f4group.citybikerent.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uwb.f4group.citybikerent.Service.BikeService;
import pl.uwb.f4group.citybikerent.Service.User;
import pl.uwb.f4group.citybikerent.model.Bike;

import java.util.List;

@Controller
@Slf4j
public class BikeRentController {
    private final BikeService bikeService;

    public BikeRentController(BikeService bikeService) {
        this.bikeService = bikeService;
    }


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

//        // Pobierz listę dostępnych rowerów z serwisu
        List<Bike> availableBikes = bikeService.getAvailableBikes();
        model.addAttribute("availableBikes", availableBikes);

        List<Bike> getAllBikes = bikeService.getAllBikes();
        model.addAttribute("getAllBikes", getAllBikes);

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
    @GetMapping("/my_rents_page")
    public String myRentsPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Sprawdź, czy użytkownik jest zalogowany
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("selectedBike", new Bike()); // Przykładowa instancja Bike
//            // Pobierz dane o wypożyczeniach danego użytkownika z serwisu
//            List<Bike> userRents = bikeService.getUserRents(loggedInUser.getId());
//            model.addAttribute("userRents", userRents);

            return "my_rents_page"; // Zwróć widok strony z informacjami o wypożyczeniach użytkownika
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Zaloguj się, aby zobaczyć swoje wypożyczenia");
            return "redirect:/login"; // Przekieruj użytkownika do formularza logowania
        }
    }


}


