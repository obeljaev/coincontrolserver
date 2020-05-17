package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.Operation;
import ru.iambelyaev.coincontrolserver.restapi.service.OperationService;

import java.util.List;

@RestController
public class OperationController {
    private final OperationService OperationService;

    @Autowired
    public OperationController(OperationService OperationService) {
        this.OperationService = OperationService;
    }

    @PostMapping(value = "/operation")
    public ResponseEntity<?> create(@RequestBody Operation Operation) {
        return OperationService.create(Operation)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<String>("not found User or " +
                "Category or FromWallet or ToWallet", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/operation/{userId}")
    public ResponseEntity<List<Operation>> read(@PathVariable(name = "userId") int userId) {
        final List<Operation> Operation = OperationService.readAll(userId);
        return Operation != null && !Operation.isEmpty()
                ? new ResponseEntity<>(Operation, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
