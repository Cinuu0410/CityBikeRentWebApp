package pl.uwb.f4group.citybikerent.Service;

public class IssueRequest {
    private String bikeNumber;
    private String issueDescription;

    // Gettery i settery
    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
}
