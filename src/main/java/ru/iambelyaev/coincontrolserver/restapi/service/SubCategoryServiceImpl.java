package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.ResultInfo;
import ru.iambelyaev.coincontrolserver.hibernate.services.SubCategoryService;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;
import ru.iambelyaev.coincontrolserver.restapi.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.SubCategoryService {
    @Override
    public ResultInfo create(SubCategory SubCategory) {
//        if(SubCategory.getSubCategoryName().isEmpty())
//            return ResultInfo.NameIsNull;
//        if(SubCategory.getCategoryId() == 0)
//            return ResultInfo.IdIsNull;
//        if(SubCategory.getSubCategoryId() != 0)
//            return ResultInfo.IdIsNotNull;
//        ru.iambelyaev.coincontrolserver.hibernate.services.SubCategoryService subCategoryService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.SubCategoryService();
//        if(subCategoryService.findCategoryByName(SubCategory.getSubCategoryName(),SubCategory.getCategoryId()).size() > 0)
//            return ResultInfo.AlreadyExist;
//
//        ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory dbSubCategory =
//                new ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory(SubCategory.getSubCategoryName());
//        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
//                categoryService.findCategory(SubCategory.getCategoryId());
//        if(dbCategory != null) {
//            dbSubCategory.setCategory(dbCategory);
//            subCategoryService.saveSubCategory(dbSubCategory);
//            return ResultInfo.OK;
//        }
        return ResultInfo.NotFoundId;
    }

    @Override
    public List<SubCategory> readAll(int categoryId) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
//            new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
//                categoryService.findCategory(categoryId);

        List<SubCategory> list = new ArrayList<>();
//        if(dbCategory != null) {
//            List<ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory> dbSubCategories =
//                    categoryService.findSubCategoryAll(dbCategory.getId());
//            for (ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory i : dbSubCategories) {
//                list.add(new SubCategory(dbCategory.getId(), i.getId(), i.getName()));
//            }
//        }
        return list;
    }

    @Override
    public ResultInfo update(SubCategory SubCategory) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
//                categoryService.findCategory(Category.getCategoryId());
//        if( dbCategory.getUser().getId() == Category.getUserId()){
//            dbCategory.setName(Category.getCategoryName());
//            categoryService.updateCategory(dbCategory);
//            return true;
//        }
//        return false;
        return ResultInfo.OK;
    }

    @Override
    public boolean subCategoryDelete(Integer categoryId, Integer subCategoryId) {
//        CategoryService categoryService =
//                new CategoryService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
//                categoryService.findCategory(categoryId);
//        if( dbCategory.getUser().getId() == userId){
//            categoryService.deleteCategory(dbCategory);
//            return true;
//        }
        return false;
    }
}
