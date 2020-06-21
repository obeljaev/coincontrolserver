package ru.iambelyaev.coincontrolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.Wallet;
import ru.iambelyaev.coincontrolserver.repository.UserRepository;
import ru.iambelyaev.coincontrolserver.repository.WalletRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CommonController {

//    @Autowired
//    private WalletRepository walletRepository;
//
//    @Autowired
//    private UserRepository userRepository;

//    @GetMapping(value = "/common/{userId}/{walletId}/{beginDate}/{lastDate}")
//    public ResponseEntity<?> read(@PathVariable Long userId,
//                                  @PathVariable Long walletId,
//                                  @PathVariable String beginDate,
//                                  @PathVariable String lastDate) {
//        if (!walletRepository.isUserWallet(walletId, userId)) {
//            throw new ResourceNotFoundException("The User does not have a Wallet");
//        }
//        final List<Wallet> Wallet = walletRepository.findByUserId(userId);
//        if (!Wallet.isEmpty()) {
//            return new ResponseEntity<>(Wallet, HttpStatus.OK);
//        } else {
//            throw new ResourceNotFoundException("Wallets not exist");
//        }
//    }
}
