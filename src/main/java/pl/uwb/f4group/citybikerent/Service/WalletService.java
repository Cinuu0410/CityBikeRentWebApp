package pl.uwb.f4group.citybikerent.Service;

import pl.uwb.f4group.citybikerent.model.User;

import java.math.BigDecimal;

public interface WalletService{
    BigDecimal getBalance(User user);
    void addToBalance(User user, BigDecimal amount);
    void deductFromBalance(User user, BigDecimal amount);
}