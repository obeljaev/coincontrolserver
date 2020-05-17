package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.SubCategory;
import ru.iambelyaev.coincontrolserver.restapi.service.SubCategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubCategoryController {
    private final SubCategoryService SubCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService SubCategoryService) {
        this.SubCategoryService = SubCategoryService;
    }

    @PostMapping(value = "/subCategory")
    public ResponseEntity<?> create(@RequestBody SubCategory SubCategory) {

//        switch (SubCategoryService.create(SubCategory)) {
//            case  OK:
//                return new ResponseEntity<>(HttpStatus.CREATED);
//            case IdIsNull:
//                return new ResponseEntity<String>("UserId is null", HttpStatus.NOT_FOUND);
//            case IdIsNotNull:
//                return new ResponseEntity<String>("Id is not equal 0", HttpStatus.NOT_FOUND);
//            case AlreadyExist:
//                return new ResponseEntity<String>("Name already exists", HttpStatus.NOT_FOUND);
//            case NotFoundId:
//                return new ResponseEntity<String>("Not found userId", HttpStatus.NOT_FOUND);
//            case NameIsNull:
//                return new ResponseEntity<String>("SubCategoryName is empty", HttpStatus.NOT_FOUND);
//            default:
//                return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<String>("Unknown error", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/subCategory/{userId}")
    public ResponseEntity<?> read(@PathVariable(name = "userId") int userId) {
//        final List<SubCategory> SubCategory = SubCategoryService.readAll(userId);
        ArrayList<SubCategory> SubCategory1 = new ArrayList<SubCategory>();
        SubCategory m_SubCategory = new SubCategory(776,777,778,"test");
        SubCategory1.add(m_SubCategory);
        return new ResponseEntity<>(SubCategory1, HttpStatus.OK);
        //        return !SubCategory.isEmpty()
//                ? new ResponseEntity<>(SubCategory, HttpStatus.OK)
//                : new ResponseEntity<String>("Not found categories/userId", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/subCategory")
    public ResponseEntity<?> update(@RequestBody SubCategory SubCategory) {
//        final boolean updated = CategoryService.update(Category);

//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
//    @PutMapping(value = "/Category/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Category Category) {
//        final boolean updated = CategoryService.update(Category, id);
//
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }

    @DeleteMapping(value = "/subCategory/{userId}/{categoryId}/{subCategoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer categoryId, @PathVariable Integer subCategoryId) {
//        return SubCategoryService.subCategoryDelete(categoryId, subCategoryId)
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
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
