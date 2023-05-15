package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private long id;

    @NotEmpty(message = "review comment cannot be empty")
    private String comment;

    @Min(value = 0l , message = "movie ID cannot be negative")
    private long movieId;


}
