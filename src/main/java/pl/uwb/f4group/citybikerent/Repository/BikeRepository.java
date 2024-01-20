package pl.uwb.f4group.citybikerent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uwb.f4group.citybikerent.model.Bike;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByAvailableTrue();

    List<Bike> findAll();

}
