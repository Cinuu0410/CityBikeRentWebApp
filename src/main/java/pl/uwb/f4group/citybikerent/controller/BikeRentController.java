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
}

