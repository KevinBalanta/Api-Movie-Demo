package com.example.demo.service;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import com.example.demo.entity.UserAPI;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService{

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO saveReview(ReviewDTO reviewDTO, String userEmail) {
        long movieId = reviewDTO.getMovieId();
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        UserAPI user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Review review = Review.builder()
                .movie(movie)
                .comment(reviewDTO.getComment())
                .userAPI(user).build();
        var savedReview = reviewRepository.save(review);
        System.out.println(savedReview);

        return ReviewResponseDTO.builder()
                .id(savedReview.getId())
                .comment(savedReview.getComment())
                .user(savedReview.getUserAPI())
                .movie(savedReview.getMovie()).build();
    }


}
