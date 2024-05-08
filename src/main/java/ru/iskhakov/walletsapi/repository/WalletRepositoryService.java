package ru.iskhakov.walletsapi.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.iskhakov.walletsapi.entity.Wallet;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class WalletRepositoryService {

    private WalletRepository walletRepository;

    public Wallet findWalletById(String id) {
        return walletRepository.findWalletById(id);
    }

    public Wallet walletDeposit(String id, BigDecimal amount) {
        return walletRepository.updateWalletDepositReturning(id, amount);
    }

    public Wallet walletWithdraw(String id, BigDecimal amount) {
        return walletRepository.updateWalletWithdrawReturning(id, amount);
    }

    public void saveWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}