package ru.iskhakov.walletsapi.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iskhakov.walletsapi.services.WalletService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static ru.iskhakov.walletsapi.utils.Objects.isNullOrEmpty;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class WalletRestController {

    private final WalletService service;

    @PostMapping(path = "/wallet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> walletOperation(@RequestBody String requestBody) {
        log.info("walletOperationById: {}", requestBody);
        if (isNullOrEmpty(requestBody)) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return ResponseEntity.ok().body(service.depositOrWithdrawById(requestBody));
    }

    @GetMapping(path = "wallets/{WALLET_UUID}")
    public ResponseEntity<String> getBalanceById(@PathVariable("WALLET_UUID") String id) {
        log.info("getBalanceById: {}", id);
        if (isNullOrEmpty(id)) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return ResponseEntity.ok().body(service.findWalletById(id));
    }
}