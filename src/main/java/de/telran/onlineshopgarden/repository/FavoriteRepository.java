package de.telran.onlineshopgarden.repository;

import de.telran.onlineshopgarden.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findByUser_UserId(Integer userId);

    boolean existsByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

    void deleteByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);
}

