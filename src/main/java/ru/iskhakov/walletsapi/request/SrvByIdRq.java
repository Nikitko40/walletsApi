package ru.iskhakov.walletsapi.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SrvByIdRq {

    private String walletId;
}