package pl.uwb.f4group.citybikerent.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uwb.f4group.citybikerent.Repository.UserRepository;
import pl.uwb.f4group.citybikerent.Repository.WalletRepository;
import pl.uwb.f4group.citybikerent.model.User;
import pl.uwb.f4group.citybikerent.model.Wallet;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BigDecimal getBalance(Long userId) {
        return userRepository.findWalletBalanceByUserId(userId);
    }

    @Override
    public void addToBalance(User user, BigDecimal amount) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Upewnij się, że wallet nie jest nullem
            existingUser.setWallet(existingUser.getWallet() != null ? existingUser.getWallet() : BigDecimal.ZERO);
            existingUser.setWallet(existingUser.getWallet().add(amount));
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
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
