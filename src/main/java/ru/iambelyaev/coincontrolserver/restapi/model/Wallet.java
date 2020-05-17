package ru.iambelyaev.coincontrolserver.restapi.model;

public class Wallet {
    private Integer walletId = 0;
    private Integer userId = 0;
    private String walletName;
    private Integer walletMoney = 0;

    public Wallet(int walletId, int userId, String walletName, int walletMoney) {
        this.walletId = walletId;
        this.userId = userId;
        this.walletName = walletName;
        this.walletMoney = walletMoney;
    }

    public Wallet() {
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public  int getWalletId() {
        return walletId;
    }
    public  void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getWalletMoney() {
        return walletMoney;
    }
    public void setWalletMoney(int walletMoney) {
        this.walletMoney = walletMoney;
    }

    public String getWalletName() {
        return walletName;
    }
    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

}