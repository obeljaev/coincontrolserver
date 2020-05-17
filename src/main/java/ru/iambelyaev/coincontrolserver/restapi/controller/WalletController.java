package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.Wallet;
import ru.iambelyaev.coincontrolserver.restapi.service.WalletService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WalletController {
    private final WalletService WalletService;

    @Autowired
    public WalletController(WalletService WalletService) {
        this.WalletService = WalletService;
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<?> create(@RequestBody Wallet Wallet) {
        switch (WalletService.create(Wallet)) {
            case  OK:
                return new ResponseEntity<>(HttpStatus.CREATED);
            case IdIsNull:
                return new ResponseEntity<String>("UserId is null", HttpStatus.NOT_FOUND);
            case IdIsNotNull:
                return new ResponseEntity<String>("Id is not equal 0", HttpStatus.NOT_FOUND);
            case AlreadyExist:
                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
            case NotFoundId:
                return new ResponseEntity<String>("Not found userId", HttpStatus.NOT_FOUND);
            case NameIsNull:
                return new ResponseEntity<String>("WalletName is empty", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/wallet/{userId}")
    public ResponseEntity<?> read(@PathVariable(name = "userId") int userId) {
        final List<Wallet> wallets = WalletService.readAll(userId);
        return !wallets.isEmpty()
                ? new ResponseEntity<>(wallets, HttpStatus.OK)
                : new ResponseEntity<String>("Not found wallets/userId", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/wallet/{userId}/{walletId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer walletId) {
        return WalletService.walletDelete(userId, walletId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<String>("Not found walletId/userId", HttpStatus.NOT_FOUND);
    }
}
