package pl.uwb.f4group.citybikerent.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BikeRentController {


    @GetMapping("/main_page_citybikerent")
    public String mainPage() {
        return "main_page_citybikerent";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/register")
    public String loginRegister() {
        return "register_page";
    }

    @GetMapping("/offer")
    public String offerPage(){
        return "offer_page";
    }

    @GetMapping("/price_list")
    public String priceListPage(){
        return "price_list_page";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact_page";
    }

    @GetMapping("/rent")
    public String rentPage(){
        return "rent_page";
    }

    @GetMapping("/regulations")
    public String regulationsPage(){
        return "regulations_page";
    }

    @GetMapping("/about_us")
    public String aboutUsPage(){
        return "about_us_page";
    }
}


