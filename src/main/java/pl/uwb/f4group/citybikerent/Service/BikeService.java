package pl.uwb.f4group.citybikerent.Service;

import pl.uwb.f4group.citybikerent.model.Bike;

import java.util.List;

public interface BikeService {
    List<Bike> getAvailableBikes();

    List<Bike> getAllBikes();


    Bike getBikeById(Long bikeId);

    void saveBike(Bike bike);

    Bike getBikeByNumber(Long currentRentalNumber);
}