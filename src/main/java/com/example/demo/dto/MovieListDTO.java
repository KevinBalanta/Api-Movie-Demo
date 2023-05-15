package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieListDTO {


    private List<MovieDTO> movies;

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean isLast;

}
