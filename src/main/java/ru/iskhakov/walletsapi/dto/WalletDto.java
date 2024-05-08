package ru.iskhakov.walletsapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class WalletDto {

    private BigDecimal walletBalance;
}