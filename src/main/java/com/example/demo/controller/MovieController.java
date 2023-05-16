package com.example.demo.controller;


import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.MovieListDTO;
import com.example.demo.service.IMovieService;
import com.example.demo.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping()
    public MovieListDTO getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER_DEFAULT, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_DEFAULT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_DIR_DEFAULT, required = false) String sortDir
    ){

        return movieService.getAllMovies(pageNo,pageSize, orderBy, sortDir);

    }


    @PostMapping()
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.saveMovie(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(movieService.updateMovie(movieDTO, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable(name = "id") long id){
        movieService.deleteMovieById(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reviews-count")
    public List<MovieDTO> getAllMovies(
            @RequestParam(value = "reviewsCount", defaultValue = "0", required = false) int count
     ){

        return movieService.getAllMoviesByReviewsAmount(count);

    }


}
