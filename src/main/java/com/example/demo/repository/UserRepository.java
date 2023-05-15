package com.example.demo.repository;

import com.example.demo.entity.UserAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAPI, Long> {
    public Optional<UserAPI> findByEmail(String email);

    public Optional<UserAPI> findByFirstnameOrEmail(String firstname, String email);

    public Optional<UserAPI> findByFirstname(String firstname);

    public Boolean existsByFirstname(String firstname);

    public Boolean existsByEmail(String email);
}
