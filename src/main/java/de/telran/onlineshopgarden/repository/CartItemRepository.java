package de.telran.onlineshopgarden.repository;

import de.telran.onlineshopgarden.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Modifying
    void deleteByProductId(Integer productId);
}
