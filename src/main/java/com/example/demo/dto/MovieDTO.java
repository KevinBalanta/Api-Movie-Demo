package com.example.demo.dto;

import com.example.demo.entity.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private long id;

    @NotEmpty(message = "movie title cannot be empty")
    private String title;

    @NotEmpty(message = "movie genre cannot be empty")
    private String genre;

    @Min(value = 0L, message = "movie rating must be positive")
    @Max(value = 10L, message = "movie rating must be max 10.0")
    private double rating;

    private Set<Review> reviews;



}
