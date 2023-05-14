package com.example.demo.repository;

import com.example.demo.entity.UserAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAPI, Long> {
    public Optional<UserAPI> findByEmail(String email);

    public Optional<UserAPI> findByUsernameOrEmail(String username, String email);

    public Optional<UserAPI> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
