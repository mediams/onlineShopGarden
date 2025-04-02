package de.telran.onlineshopgarden.repository;

import de.telran.onlineshopgarden.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserUserId(Integer userId);

    @Modifying
    void deleteByUserUserId(Integer userId);
}