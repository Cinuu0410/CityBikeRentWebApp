package pl.uwb.f4group.citybikerent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uwb.f4group.citybikerent.Repository.IssueRepository;
import pl.uwb.f4group.citybikerent.model.Issue;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public void saveIssue(String bikeNumber, String issueDescription) {
        Issue issue = new Issue();
        issue.setBikeNumber(bikeNumber);
        issue.setDescription(issueDescription);
        issueRepository.save(issue);
    }
}
