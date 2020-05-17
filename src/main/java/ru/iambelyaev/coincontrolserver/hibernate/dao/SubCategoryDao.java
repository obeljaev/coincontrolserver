package ru.iambelyaev.coincontrolserver.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SubCategoryDao {

    public SubCategory findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SubCategory subCategory = session.get(SubCategory.class, id);
        session.close();
        return subCategory;
    }

    public List<SubCategory> findByName(String name, int categoryId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<SubCategory> subCategories = (List<SubCategory>)
                session.createQuery("from SubCategory WHERE name = :sub_category_name AND category_id = :category_id")
                        .setParameter("sub_category_name", name)
                        .setParameter("category_id", categoryId)
                        .list();
        session.close();
        return subCategories;
    }

    public void save(SubCategory subCategory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(subCategory);
        tx1.commit();
        session.close();
    }

    public void update(SubCategory subCategory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(subCategory);
        tx1.commit();
        session.close();
    }

    public void delete(SubCategory subCategory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(subCategory);
        tx1.commit();
        session.close();
    }
}
