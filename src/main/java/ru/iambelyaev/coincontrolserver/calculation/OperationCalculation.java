package ru.iambelyaev.coincontrolserver.calculation;

//import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
//import ru.iambelyaev.coincontrolserver.model.User;
//import ru.iambelyaev.coincontrolserver.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import ru.iambelyaev.coincontrolserver.model.Operation;
import ru.iambelyaev.coincontrolserver.model.Wallet;
import ru.iambelyaev.coincontrolserver.repository.OperationRepository;
import ru.iambelyaev.coincontrolserver.repository.WalletRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OperationCalculation {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationCalculation.class);

    @Autowired
    private final OperationRepository operationRepository;

    @Autowired
    private final WalletRepository walletRepository;

    private Operation operation;

    private Long id;

    private Wallet toWallet;

    private Wallet fromWallet;

    public OperationCalculation(OperationRepository operationRepository, WalletRepository walletRepository) {
        this.operationRepository = operationRepository;
        this.walletRepository = walletRepository;
    }

    public Operation setProcessed(Long id){
//        LOGGER.trace("This is TRACE");
//        LOGGER.debug("This is DEBUG");
//        LOGGER.info("This is INFO");
//        LOGGER.warn("This is WARN");
//        LOGGER.error("This is ERROR");
        Optional<Operation> stock = operationRepository.findById(id);
        if (!stock.isPresent()) {
            return null;
        }
        this.operation = stock.get();
        switch(operation.getOperatationType()) {
            case MAKE:
                toWallet = operation.getTo_wallet_id();
                if(toWallet == null){
                    return null;
                }
                toWallet.setMoney(toWallet.getMoney() + operation.getMoney());
                walletRepository.save(toWallet);
                operation.setIs_processed(true);
                break;
            case SPEND:
                fromWallet = operation.getFrom_wallet_id();
                if(fromWallet == null){
                    return null;
                }
                fromWallet.setMoney(fromWallet.getMoney() - operation.getMoney());
                walletRepository.save(fromWallet);
                operation.setIs_processed(true);
                break;
            case TRANSFER:
                fromWallet = operation.getFrom_wallet_id();
                if(fromWallet == null){
                    return null;
                }
                fromWallet.setMoney(fromWallet.getMoney() - operation.getMoney());
                walletRepository.save(fromWallet);

                toWallet = operation.getTo_wallet_id();
                if(toWallet == null){
                    return null;
                }
                toWallet.setMoney(toWallet.getMoney() + operation.getMoney());
                walletRepository.save(toWallet);
                operation.setIs_processed(true);
                break;
            default:
                System.out.println("dont known");
                operation.setIs_processed(false);
        }
        operationRepository.save(operation);
        return operation;
    }
}