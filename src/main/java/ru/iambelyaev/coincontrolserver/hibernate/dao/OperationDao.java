package ru.iambelyaev.coincontrolserver.hibernate.dao;

import ru.iambelyaev.coincontrolserver.hibernate.models.Operation;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OperationDao {

    public Operation findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Operation operation = session.get(Operation.class, id);
        session.close();
        return operation;
    }

    public void save(Operation operation) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(operation);
        tx1.commit();
        session.close();
    }

    public void update(Operation operation) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(operation);
        tx1.commit();
        session.close();
    }

    public void delete(Operation operation) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(operation);
        tx1.commit();
        session.close();
    }
}
