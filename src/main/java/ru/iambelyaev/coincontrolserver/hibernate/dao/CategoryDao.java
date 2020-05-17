package ru.iambelyaev.coincontrolserver.hibernate.dao;

import ru.iambelyaev.coincontrolserver.hibernate.models.Category;
import ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDao {

    public Category findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    public List<Category> findByName(String name, int userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Category> categories = (List<Category>)
                session.createQuery("from Category WHERE name = :category_name AND user_id = :user_id")
                        .setParameter("category_name", name)
                        .setParameter("user_id", userId)
                        .list();
        session.close();
        return categories;
    }

    public void save(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(category);
        tx1.commit();
        session.close();
    }

    public void update(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(category);
        tx1.commit();
        session.close();
    }

    public void delete(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(category);
        tx1.commit();
        session.close();
    }

    public List<SubCategory> findSubCategoryAll(int category_id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<SubCategory> subCategories = (List<SubCategory>)
                session.createQuery("from SubCategory WHERE category_id = :param_category_id")
                        .setParameter("param_category_id", category_id)
                        .list();
        session.close();
        return subCategories;
    }
}
