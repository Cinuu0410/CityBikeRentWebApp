package pl.uwb.f4group.citybikerent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import pl.uwb.f4group.citybikerent.Service.WalletRepository;
import pl.uwb.f4group.citybikerent.model.User;
import pl.uwb.f4group.citybikerent.model.Wallet;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public BigDecimal getBalance(User user) {
        Wallet wallet = walletRepository.findByUser(user);
        return (wallet != null) ? wallet.getBalance() : BigDecimal.ZERO;
    }

    @Override
    public void addToBalance(User user, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUser(user);
        if (wallet == null) {
            wallet = new Wallet();
            wallet.setUser((pl.uwb.f4group.citybikerent.model.User) user);
            wallet.setBalance(amount);
        } else {
            wallet.setBalance(wallet.getBalance().add(amount));
        }
        walletRepository.save(wallet);
    }

    @Override
    public void deductFromBalance(User user, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUser(user);
        if (wallet != null && wallet.getBalance().compareTo(amount) >= 0) {
            wallet.setBalance(wallet.getBalance().subtract(amount));
            walletRepository.save(wallet);
        } else {
            throw new IllegalArgumentException("Insufficient funds in the wallet.");
        }
    }
}
