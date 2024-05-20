package pl.uwb.f4group.citybikerent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwb.f4group.citybikerent.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
