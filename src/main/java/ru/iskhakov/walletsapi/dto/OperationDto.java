package ru.iskhakov.walletsapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OperationDto {

    private String walletId;
    private OperationType operationType;
    private BigDecimal amount;
}