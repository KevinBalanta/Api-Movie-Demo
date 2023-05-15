package com.example.demo.service;


import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.MovieListDTO;
import com.example.demo.entity.Movie;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService{


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {

        Movie movie = mapEntity(movieDTO);

        Movie movieSaved = movieRepository.save(movie);

        MovieDTO movieDTOResp = mapDTO(movieSaved);

        return movieDTOResp;
    }

    @Override
    public MovieDTO updateMovie(MovieDTO movieDTO, long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setRating(movieDTO.getRating());

        Movie movieSaved = movieRepository.save(movie);

        MovieDTO movieDTOResp = mapDTO(movieSaved);

        return movieDTOResp;
    }

    @Override
    public MovieDTO getMovieById(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        MovieDTO movieResponse = mapDTO(movie);
        movieResponse.setReviews(movie.getReviews());
        System.out.println("--Movie Reviews :"+movie.getReviews().size());
        return movieResponse;

    }

    @Override
    public MovieListDTO getAllMovies(int pageNo, int pageSize, String orderBy, String orderDir) {
        Sort sort = orderDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Movie> movies = movieRepository.findAll(pageable);

        List<Movie> movieList = movies.getContent();
        List<MovieDTO> content = movieList.stream().map(movie -> mapDTO(movie))
                .collect(Collectors.toList());

        MovieListDTO moviesResponse = new MovieListDTO();
        moviesResponse.setMovies(content);
        moviesResponse.setPageNumber(movies.getNumber());
        moviesResponse.setPageSize(movies.getSize());
        moviesResponse.setTotalElements(movies.getTotalElements());
        moviesResponse.setTotalPages(movies.getTotalPages());
        moviesResponse.setLast(movies.isLast());

        return moviesResponse;
    }

    @Override
    public void deleteMovieById(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
        movieRepository.delete(movie);
    }


    private Movie mapEntity(MovieDTO movieDTO) {

        return  modelMapper.map(movieDTO, Movie.class);

    }

    private MovieDTO mapDTO(Movie movie) {

        return modelMapper.map(movie, MovieDTO.class);

    }
}
