package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.Favorite;
import de.telran.onlineshopgarden.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository repository;

    @Autowired
    public FavoriteService(FavoriteRepository repository) {
        this.repository = repository;
    }

    public List<Favorite> getAll() {
        // TODO: Аутентификация: Требуется JWT токен
        return repository.findAll();
    }
}
