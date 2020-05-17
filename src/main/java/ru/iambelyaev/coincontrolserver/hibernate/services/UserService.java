package ru.iambelyaev.coincontrolserver.hibernate.services;


import java.util.ArrayList;
import java.util.List;
import ru.iambelyaev.coincontrolserver.hibernate.dao.UserDao;
import ru.iambelyaev.coincontrolserver.hibernate.models.Operation;
import ru.iambelyaev.coincontrolserver.hibernate.models.User;
import ru.iambelyaev.coincontrolserver.hibernate.models.Category;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public List<User> findUserByName(String name) {
        return usersDao.findByName(name);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

//    public List<Operation> findOperationAll(int user_id) {
//        return usersDao.findOperationAll(user_id);
//    }

    public List<Wallet> findWalletAll(int user_id) {
        return usersDao.findWalletAll(user_id);
    }

    public Category findCategoryById(int id) {
        return usersDao.findCategoryById(id);
    }
}