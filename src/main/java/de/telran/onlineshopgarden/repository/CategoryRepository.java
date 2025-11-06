package de.telran.onlineshopgarden.repository;

import de.telran.onlineshopgarden.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @EntityGraph(attributePaths = "products")
    Optional<Category> findByCategoryId(Integer categoryId);
}
