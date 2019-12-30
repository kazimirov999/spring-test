package com.udelphi.repository;

import java.util.Set;
import com.udelphi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select p.categories from Product p where p.id =:productId")
    Set<Category> findCategoriesByProductId(int productId);
}
