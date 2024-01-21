package pl.uwb.f4group.citybikerent.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.uwb.f4group.citybikerent.model.User;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    // Tworzymy zapytanie, aby pobrać saldo portfela użytkownika na podstawie jego identyfikatora
    @Query("SELECT u.wallet FROM User u WHERE u.Id = :userId")
    BigDecimal findWalletBalanceByUserId(@Param("userId") Long userId);

}