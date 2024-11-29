package com.example.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rating.entity.Rating;
import com.example.rating.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public Rating getRatingByHotelId(Long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }


    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }
}
