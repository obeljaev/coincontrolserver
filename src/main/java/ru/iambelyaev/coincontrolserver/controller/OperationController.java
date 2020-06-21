package ru.iambelyaev.coincontrolserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.Operation;
import ru.iambelyaev.coincontrolserver.calculation.OperationCalculation;
import ru.iambelyaev.coincontrolserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OperationCalculation calculation;

    @PostMapping("/operations/{userId}/{categoryId}/{from_wallet_id}/{to_wallet_id}")
    public Operation createTransferOperation(@Valid @RequestBody Operation operation,
                                     @PathVariable Long userId,
                                     @PathVariable Long categoryId,
                                     @PathVariable Long from_wallet_id,
                                     @PathVariable Long to_wallet_id) {
        if(operation.getTime() == null){
            operation.setTime(operation.getCreation_time());
        }

        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            if(!categoryRepository.isUserSubCategory(categoryId, userId)) {
                throw new ResourceNotFoundException("The User does not have a Category");
            }
        }
        if(!walletRepository.isUserWallet(from_wallet_id, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        if(!walletRepository.isUserWallet(to_wallet_id, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    operation.setUser(user);
                    return categoryRepository.findById(categoryId)
                            .map(category -> {
                                operation.setCategory(category);
                                switch (operation.getOperatationType()){
                                    case TRANSFER:
                                        return walletRepository.findById(from_wallet_id)
                                                .map(FromWallet -> {
                                                    operation.setFrom_wallet_id(FromWallet);
                                                    return walletRepository.findById(to_wallet_id)
                                                            .map(ToWallet -> {
                                                                operation.setTo_wallet_id(ToWallet);
                                                                return saveOperation(operation);
                                                            }).orElseThrow(() -> new ResourceNotFoundException(
                                                                    "Operation not found with id " + to_wallet_id)
                                                            );
                                                }).orElseThrow(() -> new ResourceNotFoundException(
                                                        "Operation not found with id " + from_wallet_id)
                                                );
                                }
                                throw new ResourceNotFoundException("The OperationType not exist");
                            }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PostMapping("/operations/{userId}/{categoryId}/{wallet_id}")
    public Operation createOperation(@Valid @RequestBody Operation operation,
                                     @PathVariable Long userId,
                                     @PathVariable Long categoryId,
                                     @PathVariable Long wallet_id) {

        if(operation.getTime() == null){
            operation.setTime(operation.getCreation_time());
        }

        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            if(!categoryRepository.isUserSubCategory(categoryId, userId)) {
                throw new ResourceNotFoundException("The User does not have a Category");
            }
        }
        if(!walletRepository.isUserWallet(wallet_id, userId)) {
            throw new ResourceNotFoundException("The User does not have a Wallet");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    operation.setUser(user);
                    return categoryRepository.findById(categoryId)
                            .map(category -> {
                                operation.setCategory(category);
                                switch (operation.getOperatationType()) {
                                    case MAKE:
                                        return walletRepository.findById(wallet_id)
                                                .map(ToWallet -> {
                                                    operation.setTo_wallet_id(ToWallet);
                                                    return saveOperation(operation);
                                                }).orElseThrow(() -> new ResourceNotFoundException(
                                                        "Operation not found with id " + wallet_id)
                                                );
                                    case SPEND:
                                        return walletRepository.findById(wallet_id)
                                                .map(FromWallet -> {
                                                    operation.setFrom_wallet_id(FromWallet);
                                                    return saveOperation(operation);
                                                }).orElseThrow(() -> new ResourceNotFoundException(
                                                        "Operation not found with id " + wallet_id)
                                                );
                                }
                                throw new ResourceNotFoundException("The OperationType not exist");
                            }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    Operation saveOperation(Operation operation){
        Operation resultOperation = calculation.setProcessed(operationRepository.save(operation).getId());
        if (resultOperation == null) {
            throw new ResourceNotFoundException("Oooops...");
        }
        return resultOperation;
    }

    @GetMapping(value = "/operations/{userId}")
    public ResponseEntity<?> read(@PathVariable Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        List<Operation> Operation = operationRepository.findByUserId(userId);
        if(!Operation.isEmpty()) {
            return new ResponseEntity<>(Operation, HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Operation not exist");
        }
    }

    @GetMapping(value = "/operations/{userId}/{operationId}")
    public ResponseEntity<Operation> read(@PathVariable Long userId,
                                         @PathVariable Long operationId) {
        if(!operationRepository.isUserOperation(operationId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Operation");
        }
        Optional<Operation> stock = operationRepository.findById(operationId);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @DeleteMapping("/operations/{userId}/{operationId}")
    public ResponseEntity<?> deleteOperation(@PathVariable Long userId,
                                            @PathVariable Long operationId) {
        if(!operationRepository.isUserOperation(operationId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Operation");
        }

        return operationRepository.findById(operationId)
                .map(operation -> {
                    operationRepository.delete(operation);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Operation not found with id " + operationId));
    }


}