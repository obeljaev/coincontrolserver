package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;
import ru.iambelyaev.coincontrolserver.restapi.service.CategoryService;
import ru.iambelyaev.coincontrolserver.ResultInfo;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService CategoryService;

    @Autowired
    public CategoryController(CategoryService CategoryService) {
        this.CategoryService = CategoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<?> create(@RequestBody Category Category) {

        switch (CategoryService.create(Category)) {
            case  OK:
                return new ResponseEntity<>(HttpStatus.CREATED);
            case IdIsNull:
                return new ResponseEntity<String>("UserId is null", HttpStatus.NOT_FOUND);
            case IdIsNotNull:
                return new ResponseEntity<String>("Id is not equal 0", HttpStatus.NOT_FOUND);
            case AlreadyExist:
                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
            case NotFoundId:
                return new ResponseEntity<String>("Not found userId", HttpStatus.NOT_FOUND);
            case NameIsNull:
                return new ResponseEntity<String>("CategoryName is empty", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/category/{userId}")
    public ResponseEntity<?> read(@PathVariable(name = "userId") int userId) {
        final List<Category> Category = CategoryService.readAll(userId);
        return !Category.isEmpty()
                ? new ResponseEntity<>(Category, HttpStatus.OK)
                : new ResponseEntity<String>("Not found categories/userId", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/category")
    public ResponseEntity<?> update(@RequestBody Category Category) {
        switch (CategoryService.update(Category)) {
            case  OK:
                return new ResponseEntity<>(HttpStatus.CREATED);
            case IdIsNull:
                return new ResponseEntity<String>("UserId/CategoryId is null", HttpStatus.NOT_FOUND);
            case IdIsNotNull:
                return new ResponseEntity<String>("Id is not equal 0", HttpStatus.NOT_FOUND);
            case AlreadyExist:
                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
            case NotFoundId:
                return new ResponseEntity<String>("Not found userId", HttpStatus.NOT_FOUND);
            case NameIsNull:
                return new ResponseEntity<String>("CategoryName is empty", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/category/{userId}/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer categoryId) {
        return CategoryService.categoryDelete(userId, categoryId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

//    @DeleteMapping(value = "/category/{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        System.out.println(id.toString());
//        final boolean deleted = CategoryService.categoryDelete(id);
//        return deleted
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
}
