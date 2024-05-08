package ru.iskhakov.walletsapi.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.iskhakov.walletsapi.entity.Wallet;

import java.math.BigDecimal;

public interface WalletRepository extends CrudRepository<Wallet, String> {

//    <S extends Wallet> S saveAndFlush(S entity);

    @Query("select * from wallet where id = :id")
    Wallet findWalletById(String id);

    @Modifying
    @Query("update wallet " +
            "set balance = (balance + :balance) " +
            "where id = :id " +
            "RETURNING id, balance")
    Wallet updateWalletDepositReturning(String id, BigDecimal balance);

    @Modifying
    @Query("update wallet " +
            "set balance = (balance - :balance) " +
            "where id = :id " +
            "RETURNING id, balance")
    Wallet updateWalletWithdrawReturning(String id, BigDecimal balance);
}