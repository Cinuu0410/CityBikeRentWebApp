package pl.uwb.f4group.citybikerent.Controller;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uwb.f4group.citybikerent.Enum.UserRole;
import pl.uwb.f4group.citybikerent.Repository.UserRepository;
import pl.uwb.f4group.citybikerent.Service.BikeService;
import pl.uwb.f4group.citybikerent.Service.UserService;
import pl.uwb.f4group.citybikerent.Service.WalletService;
import pl.uwb.f4group.citybikerent.model.User;
import pl.uwb.f4group.citybikerent.model.Bike;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.uwb.f4group.citybikerent.Enum.UserRole.*;

@Controller
@Slf4j
public class BikeRentController {
    private final BikeService bikeService;
    private final UserService userService;
    private final WalletService walletService;
    private final UserRepository userRepository;

    public BikeRentController(BikeService bikeService, UserService userService, WalletService walletService, UserRepository userRepository) {
        this.bikeService = bikeService;
        this.userService = userService;
        this.walletService = walletService;
        this.userRepository = userRepository;
    }


    @GetMapping("/main_page_citybikerent")
    public String mainPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            System.out.println(loggedRole);
            // Zapisz informacje o zalogowanym użytkowniku i roli w sesji
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
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
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
        }

        return "offer_page";
    }

    @GetMapping("/price_list")
    public String priceListPage(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
        }
        return "price_list_page";
    }

    @GetMapping("/contact")
    public String contactPage(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
        }
        return "contact_page";
    }
    @GetMapping("/panel")
    public String panelPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);

            if (!loggedRole.equals(UserRole.SUPER_USER.getRoleName()) ) {
                // Użytkownik nie ma roli SUPER_USER, przekieruj na stronę główną
                return "redirect:/main_page_citybikerent";
            }
            // Zapisz informacje o zalogowanym użytkowniku i roli w sesji
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
            List<Bike> availableBikes = bikeService.getAvailableBikes();
            model.addAttribute("availableBikes", availableBikes);

            List<Bike> getAllBikes = bikeService.getAllBikes();
            model.addAttribute("getAllBikes", getAllBikes);
        }
        else {
            return "redirect:/login";
            }

        return "panel";
    }
    @GetMapping("/rent")
    public String rentPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
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
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
        }
        return "regulations_page";
    }

    @GetMapping("/about_us")
    public String aboutUsPage(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);
        }
        return "about_us_page";
    }
    @GetMapping("/finalize_transaction")
    public String rentPage(@RequestParam Long bikeId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Sprawdź, czy użytkownik jest zalogowany
        if (session.getAttribute("loggedInUser") != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            // Pobierz informacje o wybranym rowerze na podstawie bikeId
            Bike selectedBike = bikeService.getBikeById(bikeId);
            model.addAttribute("selectedBike", selectedBike);

            return "finalize_transaction_page";
        } else {
            // Użytkownik nie jest zalogowany, przekieruj na stronę logowania
            redirectAttributes.addFlashAttribute("message", "Zaloguj się, aby zarezerwować.");
            return "redirect:/login";
        }
    }


    @PostMapping("/finalizeReservation")
    public String finalizeReservation(@RequestParam Long bikeId, HttpSession session, RedirectAttributes redirectAttributes) {
        // Sprawdź, czy użytkownik jest zalogowany
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Sprawdź, czy użytkownik już ma wypożyczony rower
            if (loggedInUser.getCurrentRentalNumber() != null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Możesz wypożyczyć tylko jeden rower w tym samym czasie.");
                return "redirect:/rent";
            }

            // Pobierz informacje o wybranym rowerze na podstawie bikeId
            Bike selectedBike = bikeService.getBikeById(bikeId);

            // Sprawdź, czy rower jest dostępny
            if (selectedBike != null && selectedBike.isAvailable()) {
                // Zmiana statusu rezerwacji w bazie danych (ustawienie na niedostępny)
                selectedBike.setAvailable(false);
                bikeService.saveBike(selectedBike);

                // Zapisz numer roweru do danego użytkownika
                userService.saveBikeNumberToUser(loggedInUser, selectedBike.getNumber());

                // Tutaj możesz dodać dodatkowe operacje związane z finalizacją rezerwacji

                return "redirect:/rent";
            } else {
                redirectAttributes.addFlashAttribute("message", "Rower nie jest dostępny.");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Zaloguj się, aby zarezerwować.");
            return "redirect:/login";
        }

        return "redirect:/login";
    }

    @GetMapping("/my_rents")
    public String myRentsPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Sprawdź, czy użytkownik jest zalogowany
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);

            // Pobierz informacje o saldzie użytkownika
            BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
            if (walletBalance == null) {
                walletBalance = BigDecimal.ZERO;
                session.setAttribute("walletBalance", walletBalance);
            }

            // Przekaz informacje o saldzie do modelu
            model.addAttribute("walletBalance", walletBalance);

            // Pobierz informacje o aktualnym wypożyczeniu użytkownika
            Long currentRentalNumber = loggedInUser.getCurrentRentalNumber();
            if (currentRentalNumber != null) {
                // Pobierz informacje o rowerze na podstawie numeru wypożyczenia
                Bike rentedBike = bikeService.getBikeByNumber(currentRentalNumber);
                model.addAttribute("rentedBike", rentedBike);
            }

            return "my_rents_page";
        } else {
            // Użytkownik nie jest zalogowany, przekieruj na stronę logowania
            redirectAttributes.addFlashAttribute("message", "Zaloguj się, aby zobaczyć swoje wypożyczenia.");
            return "redirect:/login";
        }
    }

    @PostMapping("/return_bike")
    public ResponseEntity<Map<String, Object>> returnBike(@RequestBody Map<String, String> request, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long bikeNumber = Long.parseLong(request.get("bikeNumber"));
            boolean result = bikeService.returnBike(loggedInUser.getId(), bikeNumber);

            if (result) {
                // Ustaw CurrentRentalNumber na null dla zalogowanego użytkownika
                loggedInUser.setCurrentRentalNumber(null);
                userRepository.save(loggedInUser); // Zakładając, że userService zawiera metodę saveUser()
                return ResponseEntity.ok(Collections.singletonMap("success", true));
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Nie udało się zwrócić roweru.");
                return ResponseEntity.ok(response);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Użytkownik niezalogowany.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

        @PostMapping("/addBike")
        public String addBike(@RequestParam("bikeNumber") String bikeNumber,
                              @RequestParam("bikeBrand") String bikeBrand,
                              @RequestParam("bikeAvailable") boolean bikeAvailable) {
            Bike newBike = new Bike();
            newBike.setNumber(Long.valueOf(bikeNumber));
            newBike.setBrand(bikeBrand);
            newBike.setAvailable(bikeAvailable);

            bikeService.saveBike(newBike);

            return "redirect:/panel"; // Lub inna strona docelowa po dodaniu roweru
        }
    @PostMapping("/deleteBike/{bikeId}")
    public String deleteBike(@PathVariable Long bikeId) {
        bikeService.deleteBike(bikeId);

        return "redirect:/panel"; // Lub inna strona docelowa po usunięciu roweru
    }

    }




