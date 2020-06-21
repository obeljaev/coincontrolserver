package ru.iambelyaev.coincontrolserver.controller;

import org.aspectj.weaver.patterns.IVerificationRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.Verification;
import ru.iambelyaev.coincontrolserver.model.Wallet;
import ru.iambelyaev.coincontrolserver.repository.UserRepository;
import ru.iambelyaev.coincontrolserver.repository.VerificationRepository;
import ru.iambelyaev.coincontrolserver.repository.WalletRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class VerificationController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    @PostMapping("/verifications/{userId}/{walletId}")
    public Verification createWallet(@Valid @RequestBody Verification verification,
                                     @PathVariable Long userId,
                                     @PathVariable Long walletId) {
        if(!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    verification.setUser(user);
                    return walletRepository.findById(walletId)
                            .map(wallet -> {
                                verification.setWallet(wallet);
                                verification.setExpectedMoney(wallet.getMoney());
                                verification.setDifference(verification.getActualMoney() - verification.getExpectedMoney());
                                return verificationRepository.save(verification);
                            }).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + walletId));
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @GetMapping(value = "/verifications/{userId}")
    public ResponseEntity<?> read(@PathVariable(name = "userId") Long userId) {

        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        final List<Verification> Verification = verificationRepository.findByUserId(userId);
        if(!Verification.isEmpty()) {
            return new ResponseEntity<>(Verification, HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Verifications not exist");
        }
    }

    @GetMapping(value = "/verifications/{userId}/{walletId}/{verificationId}")
    public ResponseEntity<Verification> read(@PathVariable Long userId,
                                             @PathVariable Long walletId,
                                             @PathVariable Long verificationId) {
        if(!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        if(!verificationRepository.isWalletVerification(verificationId, walletId)) {
            throw new ResourceNotFoundException("The Wallet does not have a Verification");
        }
        Optional<Verification> stock = verificationRepository.findById(verificationId);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @GetMapping(value = "/verifications/{userId}/{walletId}")
    public ResponseEntity<?> read(@PathVariable Long userId,
                                             @PathVariable Long walletId) {
        if(!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        final List<Verification> Verification = verificationRepository.findByWalletId(walletId);
        if(!Verification.isEmpty()) {
            return new ResponseEntity<>(Verification, HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Verifications not exist");
        }
    }

    @DeleteMapping("verifications/{userId}/{walletId}/{verificationId}")
    public ResponseEntity<?> delete(@PathVariable Long userId,
                                    @PathVariable Long walletId,
                                    @PathVariable Long verificationId) {
        if(!walletRepository.isUserWallet(walletId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        if(!verificationRepository.isWalletVerification(verificationId, walletId)) {
            throw new ResourceNotFoundException("The Wallet does not have a Verification");
        }
        return verificationRepository.findById(verificationId)
                .map(verification -> {
                    verificationRepository.delete(verification);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Verification not found with id " + verificationId));
    }
}
