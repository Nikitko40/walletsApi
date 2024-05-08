package ru.iskhakov.walletsapi.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.iskhakov.walletsapi.converters.WalletToWalletDtoConverter;
import ru.iskhakov.walletsapi.dto.OperationDto;
import ru.iskhakov.walletsapi.dto.OperationType;
import ru.iskhakov.walletsapi.dto.WalletDto;
import ru.iskhakov.walletsapi.entity.Wallet;
import ru.iskhakov.walletsapi.repository.WalletRepositoryService;
import ru.iskhakov.walletsapi.request.SrvByIdRq;
import ru.iskhakov.walletsapi.response.ErrorInfo;
import ru.iskhakov.walletsapi.response.SrvByIdRs;
import ru.iskhakov.walletsapi.response.Status;
import ru.iskhakov.walletsapi.serialize.WalletSerializer;
import ru.iskhakov.walletsapi.utils.Objects;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WalletService {

    public static final String STATUS_ERROR = "1";
    public static final String STATUS_SUCCESS = "0";
    public static final String STATUS_NOT_FOUND = "7";
    public static final String NOT_FOUND_MESSAGE = "Wallet with id : %s not found";
    public static final String NOT_FOUND_OPERATION = "Unknown operation type";

    private final WalletSerializer serializer;
    private final WalletRepositoryService repository;
    private final WalletToWalletDtoConverter converter;

    public String findWalletById(String requestBody) {
        String response;
        try {
            SrvByIdRq request = serializer.deserialize(requestBody, SrvByIdRq.class);
            response = Optional.ofNullable(request.getWalletId())
                    .map(repository::findWalletById)
                    .map(converter::convert)
                    .map(this::createSuccess)
                    .map(serializer::serialize)
                    .orElseGet(() -> serializeError(String.format(NOT_FOUND_MESSAGE, request.getWalletId()), STATUS_NOT_FOUND));
        } catch (Exception e) {
            log.error("getById: {}", requestBody);
            response = serializeError(e.getMessage(), STATUS_ERROR);
        }
        return response;
    }

    public String depositOrWithdrawById(String requestBody) {
        String response = null;
        try {
            OperationDto operation = serializer.deserialize(requestBody, OperationDto.class);

            Wallet wallet;
            OperationType operationType = operation.getOperationType();
            OperationType type = Objects.applyOperationTypeIfNotNull(operationType.name(), OperationType::valueOf);
            final String walletId = operation.getWalletId();
            final BigDecimal amount = operation.getAmount();
            switch (type) {
                case DEPOSIT -> {
                    wallet = repository.walletDeposit(walletId, amount);
                    response = serializer.serialize(this.createSuccess(converter.convert(wallet)));
                }
                case WITHDRAW -> {
                    wallet = repository.walletWithdraw(walletId, amount);
                    response = serializer.serialize(this.createSuccess(converter.convert(wallet)));
                }
                case UNDEFINED -> response =
                        serializeError(String.format(NOT_FOUND_OPERATION, operation.getOperationType()), STATUS_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("getOperationById: {}", requestBody);
            response = serializeError(e.getMessage(), STATUS_ERROR);
        }
        return response;
    }

    private SrvByIdRs createErrorRs(String message, String code) {
        return SrvByIdRs.builder()
                .status(errorStatus(message, code))
                .build();
    }

    private String serializeError(String message, String code) {
        return serializer.serialize(createErrorRs(message, code));
    }

    private Status errorStatus(String value, String code) {
        return Status.builder()
                .statusCode(code)
                .errorInfo(ErrorInfo.builder()
                        .value(value)
                        .build())
                .build();
    }

    private SrvByIdRs createSuccess(WalletDto wallet) {
        return SrvByIdRs.builder()
                .wallet(wallet)
                .status(successStatus())
                .build();
    }

    private Status successStatus() {
        return Status.builder()
                .statusCode(STATUS_SUCCESS)
                .build();
    }

}
