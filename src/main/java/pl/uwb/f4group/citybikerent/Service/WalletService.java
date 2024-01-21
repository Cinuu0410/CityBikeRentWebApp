    package pl.uwb.f4group.citybikerent.Service;

    import pl.uwb.f4group.citybikerent.model.User;

    import java.math.BigDecimal;

    public interface WalletService{
        public BigDecimal getBalance(Long userId);

        void addToBalance(User user, BigDecimal amount);
        void deductFromBalance(User user, BigDecimal amount);
    }