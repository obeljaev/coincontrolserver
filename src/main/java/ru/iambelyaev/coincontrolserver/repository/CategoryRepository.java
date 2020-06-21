package ru.iambelyaev.coincontrolserver.repository;

import ru.iambelyaev.coincontrolserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Long userId);

@Query(value = "select count(*)>0 from (" +
        "select uc.cname, ucc.name from (" +
        "select c.id cid, c.name cname from categories c left join users on c.user_id = users.id where users.id = :userId) uc " +
        "left join(" +
        "select categories.category_id, categories.name from categories) ucc on (ucc.category_id = uc.cid) where (" +
        "uc.cname = :name) or (ucc.name = :name)) as existName", nativeQuery = true)
Boolean existByName(@Param("userId") Long userId,
                    @Param("name") String name);

    @Query(value = "SELECT count(*) > 0 category_id \n" +
            "            FROM categories xv LEFT JOIN categories \n" +
            "            ON xv.category_id = categories.id \n" +
            "            WHERE xv.id = :subCategoryId AND categories.id = :categoryId", nativeQuery = true)
    Boolean isCategorySubCategory(@Param("categoryId") Long categoryId,
                           @Param("subCategoryId") Long subCategoryId);

    @Query(value = "SELECT count(*) > 0 user_id " +
            "FROM categories LEFT JOIN users " +
            "ON categories.user_id = users.id " +
            "WHERE categories.id = :categoryId AND users.id = :userId", nativeQuery = true)
    Boolean isUserCategory(@Param("categoryId") Long categoryId,
                           @Param("userId") Long userId);

    @Query(value = "SELECT count(categories.user_id) > 0\n" +
            "            FROM categories subCategory LEFT JOIN categories \n" +
            "            ON subCategory.category_id = categories.id \n" +
            "            WHERE subCategory.id = :subCategoryId AND categories.user_id = :userId", nativeQuery = true)
    Boolean isUserSubCategory(@Param("subCategoryId") Long subCategoryId,
                              @Param("userId") Long userId);
}