package ru.iambelyaev.coincontrolserver.hibernate.services;

import ru.iambelyaev.coincontrolserver.hibernate.dao.SubCategoryDao;
import ru.iambelyaev.coincontrolserver.hibernate.models.SubCategory;
import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;

import java.util.List;

public class SubCategoryService {

    private SubCategoryDao subCategoryDao = new SubCategoryDao();

    public SubCategoryService() {
    }

    public SubCategory findSubCategory(int id) {
        return subCategoryDao.findById(id);
    }

    public void saveSubCategory(SubCategory subCategory) {
        subCategoryDao.save(subCategory);
    }

    public void deleteSubCategory(SubCategory subCategory) {
        subCategoryDao.delete(subCategory);
    }

    public void updateSubCategory(SubCategory subCategory) {
        subCategoryDao.update(subCategory);
    }

    public List<SubCategory> findCategoryByName(String name, int userId) {
        return subCategoryDao.findByName(name,userId);
    }
}
