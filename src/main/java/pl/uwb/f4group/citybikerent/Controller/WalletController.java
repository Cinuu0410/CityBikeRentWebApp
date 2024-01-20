package pl.uwb.f4group.citybikerent.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uwb.f4group.citybikerent.Service.WalletService;
import pl.uwb.f4group.citybikerent.model.User;
import java.math.BigDecimal;

@Controller
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }



    @PostMapping("/wallet/addFunds")
    public String addFunds(@RequestParam("amount") BigDecimal amount, Model model, HttpSession session) {
        // Pobierz aktualny stan portfela z sesji
        BigDecimal currentBalance = (BigDecimal) session.getAttribute("walletBalance");

        // Sprawdź, czy currentBalance nie jest nullem
        if (currentBalance == null) {
            // Jeśli jest nullem, ustaw na zero
            currentBalance = BigDecimal.ZERO;
        }

        // Dodaj wprowadzoną kwotę do aktualnego salda
        BigDecimal newBalance = currentBalance.add(amount);

        // Zapisz nowe saldo w sesji
        session.setAttribute("walletBalance", newBalance);

        // Przekieruj użytkownika z powrotem do strony portfela
        return "redirect:/wallet";
    }
    @GetMapping("/wallet")
    public String viewWallet(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Przekieruj na stronę logowania, jeśli użytkownik nie jest zalogowany
            return "redirect:/login";
        }
        model.addAttribute("loggedInUser", loggedInUser);
        BigDecimal walletBalance = (BigDecimal) session.getAttribute("walletBalance");
        if (walletBalance == null) {
            walletBalance = BigDecimal.ZERO;
            session.setAttribute("walletBalance", walletBalance);
        }

        // Przekaz informacje o saldzie do modelu
        model.addAttribute("walletBalance", walletBalance);

        // Wyrenderuj widok portfela
        return "wallet";
    }

    @PostMapping("/deductFromWallet")
    public String deductFromWallet(@RequestParam BigDecimal amount, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        walletService.deductFromBalance(loggedInUser, amount);
        return "redirect:/wallet";
    }
}
