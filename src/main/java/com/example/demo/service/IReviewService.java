package com.example.demo.service;

import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewResponseDTO;

public interface IReviewService {

    public ReviewResponseDTO saveReview(ReviewDTO reviewDTO, String userEmail);


}
