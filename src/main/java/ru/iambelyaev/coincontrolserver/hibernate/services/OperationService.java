package ru.iambelyaev.coincontrolserver.hibernate.services;

import ru.iambelyaev.coincontrolserver.hibernate.dao.OperationDao;
import ru.iambelyaev.coincontrolserver.hibernate.models.Operation;

public class OperationService {

    private OperationDao operationDao = new OperationDao();

    public OperationService() {
    }

    public Operation findOperation(int id) {
        return operationDao.findById(id);
    }

    public void saveOperation(Operation operation) {
        operationDao.save(operation);
    }

    public void deleteOperation(Operation operation) {
        operationDao.delete(operation);
    }

    public void updateOperation(Operation operation) {
        operationDao.update(operation);
    }
}
