package com.example.demo.dto;

import com.example.demo.entity.Movie;
import com.example.demo.entity.UserAPI;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

    private long id;

    private String comment;

    private Movie movie;

    private UserAPI user;




}
