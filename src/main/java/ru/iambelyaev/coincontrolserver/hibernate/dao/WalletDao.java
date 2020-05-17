package ru.iambelyaev.coincontrolserver.hibernate.dao;

import ru.iambelyaev.coincontrolserver.hibernate.models.User;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WalletDao {

    public Wallet findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Wallet wallet = session.get(Wallet.class, id);
        session.close();
        return wallet;
    }

    public List<Wallet> findByName(String name, int userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Wallet> wallets = (List<Wallet>)
                session.createQuery("from Wallet WHERE name = :wallet_name AND user_id = :user_id")
                        .setParameter("wallet_name", name)
                        .setParameter("user_id", userId)
                        .list();
        session.close();
        return wallets;
    }

    public void save(Wallet wallet) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(wallet);
        tx1.commit();
        session.close();
    }

    public void update(Wallet wallet) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(wallet);
        tx1.commit();
        session.close();
    }

    public void delete(Wallet wallet) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(wallet);
        tx1.commit();
        session.close();
    }
}
