package ru.iambelyaev.coincontrolserver.restapi.service;

import ru.iambelyaev.coincontrolserver.restapi.model.Operation;

import java.util.List;

public interface OperationService {
    boolean create(Operation Operation);

    List<Operation> readAll(int userId);
}
