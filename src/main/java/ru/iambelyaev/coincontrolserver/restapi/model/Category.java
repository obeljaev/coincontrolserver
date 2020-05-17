package ru.iambelyaev.coincontrolserver.restapi.model;

public class Category {
    private Integer userId = 0;
    private Integer categoryId = 0;
    private String categoryName;
//    private Integer subCategoryId = 0;
//    private String subCategoryName;

    public Category(int userId, int categoryId, String categoryName, int subCategoryId, String subCategoryName) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
//        this.subCategoryId = subCategoryId;
//        this.subCategoryName = subCategoryName;
    }

    public Category() {
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
//
//    public int getSubCategoryId() {
//        return subCategoryId;
//    }
//
//    public void setSubCategoryId(int subCategoryId) {
//        this.subCategoryId = subCategoryId;
//    }
//
//    public String getSubCategoryName() {
//        return subCategoryName;
//    }
//
//    public void setSubCategoryName(String subCategoryName) {
//        this.subCategoryName = subCategoryName;
//    }
}