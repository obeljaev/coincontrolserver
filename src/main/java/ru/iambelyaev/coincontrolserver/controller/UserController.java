package ru.iambelyaev.coincontrolserver.controller;

import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.User;
import ru.iambelyaev.coincontrolserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        if(userRepository.existByName(user.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId,
                           @Valid @RequestBody User userRequest) {
        if(userRepository.existByName(userRequest.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }

        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(userRequest.getName());
                    user.setPassword(userRequest.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        Optional<User> stock = userRepository.findById(userId);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> readAll() {
        final List<User> User = userRepository.findAll();
        return !User.isEmpty()
                ? new ResponseEntity<>(User, HttpStatus.OK)
                : new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}