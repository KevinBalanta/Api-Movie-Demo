package com.example.demo.repository;


import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT m.* FROM movies m JOIN reviews r ON m.id = r.movie_id GROUP BY m.id, m.title HAVING COUNT(r.movie_id) >= ?1", nativeQuery = true)
    List<Movie> findByReviewsAmount(int count);
}
