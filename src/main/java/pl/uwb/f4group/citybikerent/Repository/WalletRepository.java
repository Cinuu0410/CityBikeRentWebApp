package pl.uwb.f4group.citybikerent.Repository;

import pl.uwb.f4group.citybikerent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.uwb.f4group.citybikerent.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}