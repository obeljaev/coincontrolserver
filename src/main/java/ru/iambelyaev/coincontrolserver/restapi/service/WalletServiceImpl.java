package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.ResultInfo;
import ru.iambelyaev.coincontrolserver.restapi.model.Wallet;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.WalletService {
    @Override
    public ResultInfo create(Wallet Wallet) {
        if(Wallet.getWalletName().isEmpty())
            return ResultInfo.NameIsNull;
        if(Wallet.getUserId() == 0)
            return ResultInfo.IdIsNull;
        if(Wallet.getWalletId() != 0)
            return ResultInfo.IdIsNotNull;
        ru.iambelyaev.coincontrolserver.hibernate.services.WalletService walletService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.WalletService();
        if(walletService.findWalletByName(Wallet.getWalletName(),Wallet.getUserId()).size() > 0)
            return ResultInfo.AlreadyExist;
        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbWallet =
                new ru.iambelyaev.coincontrolserver.hibernate.models.Wallet(Wallet.getWalletName(), Wallet.getWalletMoney());

        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser = userService.findUser(Wallet.getUserId());
        if(dbUser != null) {
            dbWallet.setUser(dbUser);
            walletService.saveWallet(dbWallet);
            return ResultInfo.OK;
        }
        return ResultInfo.NotFoundId;
    }

    @Override
    public List<Wallet> readAll(int userId) {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(userId);

        List<Wallet> list = new ArrayList<>();
        if(dbUser != null) {
            List<ru.iambelyaev.coincontrolserver.hibernate.models.Wallet> dbWallets = userService.findWalletAll(userId);
            for (ru.iambelyaev.coincontrolserver.hibernate.models.Wallet i : dbWallets) {
                list.add(new Wallet(i.getId(), dbUser.getId(), i.getName(), i.getMoney()));
            }
        }
        return list;
    }



    @Override
    public boolean walletDelete(Integer userId, Integer walletId) {
        ru.iambelyaev.coincontrolserver.hibernate.services.WalletService walletService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.WalletService();
        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbWallet =
                walletService.findWallet(walletId);

        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(userId);

        if(( dbUser != null) && (dbWallet != null)){
            walletService.deleteWallet(dbWallet);
            return true;
        }
        return false;
    }
}
