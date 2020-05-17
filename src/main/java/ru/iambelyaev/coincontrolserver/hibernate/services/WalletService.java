package ru.iambelyaev.coincontrolserver.hibernate.services;

import ru.iambelyaev.coincontrolserver.hibernate.dao.WalletDao;
import ru.iambelyaev.coincontrolserver.hibernate.models.User;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;

import java.util.List;

public class WalletService {

    private WalletDao walletDao = new WalletDao();

    public WalletService() {
    }

    public Wallet findWallet(int id) {
        return walletDao.findById(id);
    }

    public void saveWallet(Wallet wallet) {
        walletDao.save(wallet);
    }

    public void deleteWallet(Wallet wallet) {
        walletDao.delete(wallet);
    }

    public void updateWallet(Wallet wallet) {
        walletDao.update(wallet);
    }

    public List<Wallet> findWalletByName(String name, int userId) {
        return walletDao.findByName(name,userId);
    }

}
