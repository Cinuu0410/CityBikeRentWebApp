package pl.uwb.f4group.citybikerent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import pl.uwb.f4group.citybikerent.Repository.BikeRepository;
import pl.uwb.f4group.citybikerent.model.Bike;

@Service
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public List<Bike> getAvailableBikes() {
        // Implementacja zwracająca dostępne rowery
        return bikeRepository.findByAvailableTrue();
    }

    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike getBikeById(Long bikeId) {
        return bikeRepository.findById(bikeId).orElse(null);
    }
    public void saveBike(Bike bike) {
        bikeRepository.save(bike);
    }
    @Override
    public void deleteBike(Long bikeId) {
        bikeRepository.deleteById(bikeId);
    }
    public void editBike(Bike editedBike) {
        // Tutaj możesz dodać logikę walidacji lub inne operacje związane z edycją roweru
        bikeRepository.save(editedBike);
    }

    @Override
    public boolean returnBike(Long userId, Long bikeNumber) {
        Optional<Bike> bikeOptional = bikeRepository.findByNumber(bikeNumber);
        if (bikeOptional.isPresent()) {
            Bike bike = bikeOptional.get();
            if (!bike.isAvailable()) {
                bike.setAvailable(true);
                bikeRepository.save(bike);
                // Dodatkowa logika, np. aktualizacja stanu użytkownika, usunięcie wypożyczenia, itp.
                return true;
            }
        }
        return false;
    }

    @Override
    public Bike getBikeByNumber(Long number) {
        return bikeRepository.findByNumber(number).orElse(null);
    }


}
