package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.restapi.model.Operation;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class OperationServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.OperationService {
    @Override
    public boolean create(Operation Operation) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser = userService.findUser(Operation.getUser());
//
//        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
//                categoryService.findCategory(Operation.getCategory());
//
//        ru.iambelyaev.coincontrolserver.hibernate.services.WalletService walletService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.WalletService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbFromWallet =
//                walletService.findWallet(Operation.getFromWallet());
//        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbToWallet =
//                walletService.findWallet(Operation.getToWallet());
////        System.out.println(Operation.getUser() + " " +
////                Operation.getCategory() + " " +
////                Operation.getFromWallet() + " " +
////                Operation.getToWallet());
//
//        if(dbUser != null &&
//                dbCategory != null &&
//                dbFromWallet != null &&
//                dbToWallet != null) {
//            ru.iambelyaev.coincontrolserver.hibernate.services.OperationService operationService =
//                    new ru.iambelyaev.coincontrolserver.hibernate.services.OperationService();
//            ru.iambelyaev.coincontrolserver.hibernate.models.Operation dbOperation =
//                    new ru.iambelyaev.coincontrolserver.hibernate.models.Operation(
//                            LocalDateTime.now(),//.getCreationTime(),
//                            LocalDateTime.now(),//Operation.getOperationTime(),
//                            Operation.getType(),
//                            Operation.getMoney(),
//                            false
//                    );
//            dbOperation.setUser(dbUser);
//            dbOperation.setCategory(dbCategory);
//            dbOperation.setFrom_wallet_id(dbFromWallet);
//            dbOperation.setTo_wallet_id(dbToWallet);
//            operationService.saveOperation(dbOperation);
//            return true;
//        }
        return false;
    }

    @Override
    public List<Operation> readAll(int userId) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
//                userService.findUser(userId);
//
        List<Operation> list = new ArrayList<>();
//        if(dbUser != null) {
//            List<ru.iambelyaev.coincontrolserver.hibernate.models.Operation> dbOperation =
//                    userService.findOperationAll(dbUser.getId());
//            for (ru.iambelyaev.coincontrolserver.hibernate.models.Operation i : dbOperation) {
//                list.add(new Operation(dbUser.getId(),
//                        i.getCategory().getId(),
//                        i.getCreationTime(),
//                        i.getOperationTime(),
//                        i.getType(),
//                        i.getFrom_wallet_id().getId(),
//                        i.getTo_wallet_id().getId(),
//                        i.getMoney()));
//            }
//        }
        return list;
    }
}
