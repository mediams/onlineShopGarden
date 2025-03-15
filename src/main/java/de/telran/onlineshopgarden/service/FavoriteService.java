package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.Favorite;
import de.telran.onlineshopgarden.entity.Product;
import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.repository.FavoriteRepository;
import de.telran.onlineshopgarden.repository.ProductRepository;
import de.telran.onlineshopgarden.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Favorite> getAll() {
        // TODO: JWT аутентификация
        return favoriteRepository.findAll();
    }

    @Transactional
    public void addToFavorites(Integer userId, Integer productId) {
        if (favoriteRepository.existsByUser_UserIdAndProduct_ProductId(userId, productId)) {
            throw new RuntimeException("Item is already added to favorites");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
    }

    public List<Product> getFavoriteProducts(Integer userId) {
        return favoriteRepository.findByUser_UserId(userId).stream()
                .map(Favorite::getProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeFromFavorites(Integer userId, Integer productId) {
        favoriteRepository.deleteByUser_UserIdAndProduct_ProductId(userId, productId);
    }
}
