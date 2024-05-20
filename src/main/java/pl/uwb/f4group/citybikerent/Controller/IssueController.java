package pl.uwb.f4group.citybikerent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uwb.f4group.citybikerent.Service.IssueRequest;
import pl.uwb.f4group.citybikerent.Service.IssueService;

@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/report_issue")
    public String reportIssue(@RequestBody IssueRequest issueRequest, Model model) {
        issueService.saveIssue(issueRequest.getBikeNumber(), issueRequest.getIssueDescription());
        model.addAttribute("successMessage", "Zgłoszenie zostało wysłane");
        return "my_rents_page";
    }
}

