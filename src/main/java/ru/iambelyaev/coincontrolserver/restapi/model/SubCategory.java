package ru.iambelyaev.coincontrolserver.restapi.model;

public class SubCategory {
    private Integer userId = 0;
    private Integer categoryId = 0;
    private Integer subCategoryId = 0;
    private String subCategoryName;

    public SubCategory(int userId, int categoryId, int subCategoryId, String subCategoryName) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }

    public SubCategory() {
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

}