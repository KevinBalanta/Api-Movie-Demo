package com.example.demo.service;


import com.example.demo.dto.MovieListDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewListDTO;
import com.example.demo.dto.ReviewResponseDTO;


public interface IReviewService {

    public ReviewResponseDTO saveReview(ReviewDTO reviewDTO, String userEmail);

    public ReviewResponseDTO updateReview(ReviewDTO reviewDTO, String userEmail, long id);

    public ReviewResponseDTO getReviewById(long id);

    public void deleteReview(long id);

    public ReviewListDTO getAllReviews(int pageNo, int pageSize, String orderBy, String orderDir, long movieId);







    }
