package ru.iambelyaev.coincontrolserver.controller;

import ru.iambelyaev.coincontrolserver.exception.ResourceNotFoundException;
import ru.iambelyaev.coincontrolserver.model.Category;
import ru.iambelyaev.coincontrolserver.model.User;
import ru.iambelyaev.coincontrolserver.repository.CategoryRepository;
import ru.iambelyaev.coincontrolserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/categories/{userId}")
    public Category createCategory(@Valid @RequestBody Category category,
                                   @PathVariable Long userId) {
        if(categoryRepository.existByName(userId, category.getName())) {
        //if(categoryRepository.existByName(category.getName())) {
                throw new ResourceNotFoundException("Name already exists");
            }

        return userRepository.findById(userId)
                .map(user -> {
                    category.setUser(user);
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PostMapping("/categories/{userId}/{categoryId}")
    public Category createSubCategory(@Valid @RequestBody Category subCategory,
                                   @PathVariable Long userId,
                                   @PathVariable Long categoryId) {
        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            throw new ResourceNotFoundException("The User does not have a Category");
        }
        if(categoryRepository.existByName(userId,subCategory.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }
        return userRepository.findById(userId)
                .map(user -> {
                    if(categoryId != 0){
                        return categoryRepository.findById(categoryId)
                                .map(category -> {
                                    subCategory.setCategory(category);
                                    return categoryRepository.save(subCategory);
                                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
                    } else {
                        subCategory.setUser(user);
                        return categoryRepository.save(subCategory);
                    }
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/categories/{userId}/{categoryId}")
    public Category updateCategory(@Valid @RequestBody Category categoryReq,
                           @PathVariable Long userId,
                           @PathVariable Long categoryId) {
        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            if(!categoryRepository.isUserSubCategory(categoryId, userId)) {
                throw new ResourceNotFoundException("The User does not have a Category");
            }
        }
        if(categoryRepository.existByName(userId, categoryReq.getName())) {
            throw new ResourceNotFoundException("Name already exists");
        }
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setName(categoryReq.getName());
                    return categoryRepository.save(category);
                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
    }

    @GetMapping(value = "/categories/{userId}")
    public ResponseEntity<?> read(@PathVariable Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        final List<Category> Category = categoryRepository.findByUserId(userId);
        if(!Category.isEmpty()) {
            return new ResponseEntity<>(Category, HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Categories not exist");
        }
    }

    @GetMapping(value = "/categories/{userId}/{categoryId}")
    public ResponseEntity<Category> read(@PathVariable Long userId,
                                  @PathVariable Long categoryId) {
        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            if(!categoryRepository.isUserSubCategory(categoryId, userId)) {
                throw new ResourceNotFoundException("The User does not have a Category");
            }
        }
        Optional<Category> stock = categoryRepository.findById(categoryId);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @DeleteMapping("/categories/{userId}/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long userId,
                                          @PathVariable Long categoryId) {
        if(!categoryRepository.isUserCategory(categoryId, userId)) {
            if(!categoryRepository.isUserSubCategory(categoryId, userId)) {
                throw new ResourceNotFoundException("The User does not have a Category");
            }
        }
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
    }
}