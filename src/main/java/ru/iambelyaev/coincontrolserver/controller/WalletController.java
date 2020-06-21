package ru.iambelyaev.coincontrolserver.controller;

import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.Wallet;
import ru.iambelyaev.coincontrolserver.repository.WalletRepository;
import ru.iambelyaev.coincontrolserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/wallets/{userId}")
    public Wallet createWallet(@Valid @RequestBody Wallet wallet,
                               @PathVariable Long userId) {
        if (walletRepository.existByName(userId, wallet.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    wallet.setUser(user);
                    return walletRepository.save(wallet);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @GetMapping(value = "/wallets/{userId}")
    public ResponseEntity<?> read(@PathVariable(name = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        final List<Wallet> Wallet = walletRepository.findByUserId(userId);
        if (!Wallet.isEmpty()) {
            return new ResponseEntity<>(Wallet, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Wallets not exist");
        }
    }

    @GetMapping(value = "/wallets/{userId}/{walletId}")
    public ResponseEntity<Wallet> read(@PathVariable(name = "userId") Long userId,
                                       @PathVariable(name = "walletId") Long walletId) {
        if (!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        Optional<Wallet> stock = walletRepository.findById(walletId);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/wallets/{userId}/{walletId}")
    public Wallet updateCategory(@Valid @RequestBody Wallet walletRequest,
                                 @PathVariable Long userId,
                                 @PathVariable Long walletId) {
        if (!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        if (walletRepository.existByName(userId, walletRequest.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }
        return walletRepository.findById(walletId)
                .map(wallet -> {
                    wallet.setName(walletRequest.getName());
                    return walletRepository.save(wallet);
                }).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + walletId));
    }

    @DeleteMapping("/wallets/{userId}/{walletId}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long userId,
                                          @PathVariable Long walletId) {
        if (!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        return walletRepository.findById(walletId)
                .map(wallet -> {
                    walletRepository.delete(wallet);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + walletId));
    }
}