package com.dea.management.app.user.repository;

import com.dea.management.app.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE name = :name")
    Optional<User> findByName(String name);
}
