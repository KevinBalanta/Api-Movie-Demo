package com.example.demo.controller;

import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.service.IReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
