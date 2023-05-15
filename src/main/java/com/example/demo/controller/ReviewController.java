package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.IReviewService;
import com.example.demo.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    @Autowired
    private IReviewService reviewService;

    @PostMapping()
    public ResponseEntity<ReviewResponseDTO> saveReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(reviewService.saveReview(reviewDTO, userEmail), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable(name = "id") long id) {
        ReviewResponseDTO review = reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReviewById(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable(name = "id") long id) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(reviewService.updateReview(reviewDTO, userEmail, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable(name = "id") long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }


    @GetMapping()
    public ReviewListDTO getAllMovies(
            @RequestParam(value = "movieId", defaultValue = AppConstants.MOVIE_ID_DEFAULT, required = false) Long movieId,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER_DEFAULT, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_DEFAULT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_DIR_DEFAULT, required = false) String sortDir
    ){

        return reviewService.getAllReviews(pageNo,pageSize, orderBy, sortDir, movieId);

    }


}
