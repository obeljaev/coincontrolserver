package ru.iambelyaev.coincontrolserver.restapi.service;

import ru.iambelyaev.coincontrolserver.ResultInfo;
import ru.iambelyaev.coincontrolserver.restapi.model.Wallet;

import java.util.List;

public interface WalletService {
    ResultInfo create(Wallet Wallet);

    List<Wallet> readAll(int userId);

    boolean walletDelete(Integer userId, Integer walletId);

}
