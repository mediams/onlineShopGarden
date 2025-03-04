package de.telran.onlineshopgarden.service;

import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public User create(User user) {
        // TODO: add password encryption
        return repository.save(user);
    }

    public void update(Integer id, User user) {
        int updated = repository.update(id, user.getName(), user.getPhoneNumber());
        if (updated == 0)
            throw new ResourceNotFoundException("User with id " + id + " not found");
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        repository.deleteByIdWithoutSelect(id);
    }
}
