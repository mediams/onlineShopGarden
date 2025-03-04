package de.telran.onlineshopgarden.repository;

import de.telran.onlineshopgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("UPDATE User u SET u.name = :name, u.phoneNumber = :phoneNumber WHERE u.userId = :id")
    @Modifying
    @Transactional
    int update(Integer id, String name, String phoneNumber);

    @Query("DELETE FROM User u WHERE u.userId = :id")
    @Modifying
    @Transactional
    void deleteByIdWithoutSelect(Integer id);
}
