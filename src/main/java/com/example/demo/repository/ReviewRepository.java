package com.example.demo.repository;

import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public Page<Review> findByMovie(Movie movie, Pageable pageable);
}
