package com.example.demo.service;


import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.MovieListDTO;

public interface IMovieService {

    public MovieDTO saveMovie(MovieDTO movieDTO);

    public MovieDTO updateMovie(MovieDTO movieDTO, long id);

    public MovieDTO getMovieById(long id);

    public MovieListDTO getAllMovies(int pageNo, int pageSize, String orderBy, String orderDir);

    public void deleteMovieById(long id);
}
