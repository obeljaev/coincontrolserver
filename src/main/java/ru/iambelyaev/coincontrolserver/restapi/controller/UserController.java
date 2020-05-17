package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.User;
import ru.iambelyaev.coincontrolserver.restapi.service.UserService;
import ru.iambelyaev.coincontrolserver.ResultInfo;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService UserService;
    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }
    @PostMapping(value = "/user")
    public ResponseEntity<?> create(@RequestBody User User) {
        switch (UserService.create(User)) {
            case  OK:
                return new ResponseEntity<>(HttpStatus.CREATED);
            case NameIsNull:
                return new ResponseEntity<String>("Name is null", HttpStatus.NOT_FOUND);
            case PasswordIsNull:
                return new ResponseEntity<String>("Password is null", HttpStatus.NOT_FOUND);
            case IdIsNotNull:
                return new ResponseEntity<String>("Id is not equal 0", HttpStatus.NOT_FOUND);
            case AlreadyExist:
                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> read() {
        final List<User> User = UserService.readAll();
        return User != null && !User.isEmpty()
                ? new ResponseEntity<>(User, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> update(@RequestBody User User) {
        switch (UserService.update(User)) {
            case  OK:
                return new ResponseEntity<>(HttpStatus.OK);
            case NameIsNull:
                return new ResponseEntity<String>("Name is null", HttpStatus.NOT_FOUND);
            case PasswordIsNull:
                return new ResponseEntity<String>("Password is null", HttpStatus.NOT_FOUND);
            case IdIsNull:
                return new ResponseEntity<String>("Id is null", HttpStatus.NOT_FOUND);
            case AlreadyExist:
                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
            case NotFoundId:
                return new ResponseEntity<String>("Not found userId", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        System.out.println("0--------------");
        return UserService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<String>("not found user_id", HttpStatus.NOT_FOUND);
    }
}