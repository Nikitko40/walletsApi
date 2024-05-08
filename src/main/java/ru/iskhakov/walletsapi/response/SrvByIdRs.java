package ru.iskhakov.walletsapi.response;

import lombok.Builder;
import lombok.Getter;
import ru.iskhakov.walletsapi.dto.WalletDto;

@Getter
@Builder
public class SrvByIdRs {

    private WalletDto wallet;
    private Status status;
}