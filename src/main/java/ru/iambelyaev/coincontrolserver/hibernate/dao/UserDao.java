package ru.iambelyaev.coincontrolserver.hibernate.dao;

import ru.iambelyaev.coincontrolserver.hibernate.models.Operation;
import ru.iambelyaev.coincontrolserver.hibernate.models.User;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.*;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;
import java.util.List;
import ru.iambelyaev.coincontrolserver.hibernate.models.Category;

public class UserDao {

    public User findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> users = (List<User>)
                session.createQuery("from User WHERE user_name = :param_user_name")
                        .setParameter("param_user_name", name)
                        .list();
        session.close();
        return users;
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Category findCategoryById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Category.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("From User").list();
        return users;
    }

//    public List<Operation> findOperationAll(int user_id) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        List<Operation> operations = (List<Operation>)
//                session.createQuery("from Operation WHERE user_id = :param_user_id")
//                        .setParameter("param_user_id", user_id)
//                        .list();
//        session.close();
//        return operations;
//    }
//
    public List<Wallet> findWalletAll(int user_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Wallet> wallets = (List<Wallet>)
                session.createQuery("from Wallet WHERE user_id = :param_user_id")
                        .setParameter("param_user_id", user_id)
                        .list();
        session.close();
        return wallets;
    }
}