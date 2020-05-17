package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.restapi.model.User;
import ru.iambelyaev.coincontrolserver.hibernate.services.UserService;
import ru.iambelyaev.coincontrolserver.hibernate.models.*;
import ru.iambelyaev.coincontrolserver.ResultInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.UserService {
    @Override
    public ResultInfo create(User User) {
        if(User.getUserName().isEmpty())
            return ResultInfo.NameIsNull;
        if(User.getUserPassword().isEmpty())
            return ResultInfo.PasswordIsNull;
        if(User.getUserId() != 0)
            return ResultInfo.IdIsNotNull;
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        if(userService.findUserByName(User.getUserName()).size() > 0)
            return ResultInfo.AlreadyExist;
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                new ru.iambelyaev.coincontrolserver.hibernate.models.User(User.getUserName(),User.getUserPassword());
        userService.saveUser(dbUser);
        return ResultInfo.OK;
    }

    @Override
    public List<User> readAll() {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        List<ru.iambelyaev.coincontrolserver.hibernate.models.User> dbUser = userService.findAllUsers();
        ArrayList<User> list = new ArrayList<>();
        for(ru.iambelyaev.coincontrolserver.hibernate.models.User i : dbUser){
            list.add(new User(i.getId(), i.getName(), i.getPassword()));
        }
        return list;
    }

    @Override
    public ResultInfo update(ru.iambelyaev.coincontrolserver.restapi.model.User User) {
        if(User.getUserName().isEmpty())
            return ResultInfo.NameIsNull;
        if(User.getUserPassword().isEmpty())
            return ResultInfo.PasswordIsNull;
        if(User.getUserId() == 0)
            return ResultInfo.IdIsNull;
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        if(userService.findUserByName(User.getUserName()).size() > 0)
            return ResultInfo.AlreadyExist;
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(User.getUserId());
        if( dbUser != null){
            dbUser.setName(User.getUserName());
            dbUser.setPassword(User.getUserPassword());
            userService.updateUser(dbUser);
            return ResultInfo.OK;
        }
        return ResultInfo.NotFoundId;
    }

    @Override
    public boolean delete(int id) {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(id);
        if(dbUser != null){
            userService.deleteUser(dbUser);
            return true;
        }
        return false;
    }
}