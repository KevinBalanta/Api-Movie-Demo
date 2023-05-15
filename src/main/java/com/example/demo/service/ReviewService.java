package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import com.example.demo.entity.UserAPI;
import com.example.demo.exceptions.MovieAPIException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ReviewResponseDTO updateReview(ReviewDTO reviewDTO, String userEmail, long id) {

        UserAPI user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", reviewDTO.getId()));
        if(review.getUserAPI().getId() != user.getId()) {
            throw new MovieAPIException("invalid user id", HttpStatus.BAD_REQUEST);
        }

        review.setComment(reviewDTO.getComment());

        review = reviewRepository.save(review);

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .comment(review.getComment())
                .user(review.getUserAPI())
                .movie(review.getMovie()).build();
    }

    @Override
    public ReviewListDTO getAllReviews(int pageNo, int pageSize, String orderBy, String orderDir, long movieId) {
        Sort sort = orderDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Review> reviews;
        if(movieId != -1) {

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
            reviews = reviewRepository.findByMovie(movie, pageable);
        } else {
            reviews = reviewRepository.findAll(pageable);
        }



        List<Review> reviewList = reviews.getContent();
        List<ReviewResponseDTO> content = reviewList.stream().map(review -> {
                            return ReviewResponseDTO.builder()
                                    .id(review.getId())
                                    .comment(review.getComment())
                                    .movie(review.getMovie())
                                    .user(review.getUserAPI()).build();
                        }
                )
                .collect(Collectors.toList());

        ReviewListDTO reviewsResponse = new ReviewListDTO();
        reviewsResponse.setReviewList(reviewList);
        reviewsResponse.setPageNumber(reviews.getNumber());
        reviewsResponse.setPageSize(reviews.getSize());
        reviewsResponse.setTotalElements(reviews.getTotalElements());
        reviewsResponse.setTotalPages(reviews.getTotalPages());
        reviewsResponse.setLast(reviews.isLast());

        return reviewsResponse;
    }


    @Override
    public ReviewResponseDTO getReviewById(long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .comment(review.getComment())
                .user(review.getUserAPI())
                .movie(review.getMovie()).build();
    }

    @Override
    public void deleteReview(long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        reviewRepository.delete(review);
    }


}
