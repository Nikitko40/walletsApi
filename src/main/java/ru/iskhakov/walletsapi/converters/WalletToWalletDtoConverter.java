package ru.iskhakov.walletsapi.converters;

import org.springframework.stereotype.Component;
import ru.iskhakov.walletsapi.dto.WalletDto;
import ru.iskhakov.walletsapi.entity.Wallet;

@Component
public class WalletToWalletDtoConverter {

    public WalletDto convert(Wallet source) {
        if (source == null) {
            return null;
        }
        return WalletDto.builder()
                .walletBalance(source.getBalance())
                .build();
    }
}