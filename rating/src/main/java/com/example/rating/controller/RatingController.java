package com.example.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rating.entity.Rating;
import com.example.rating.service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Long id) {
        Rating rating = ratingService.getRatingById(id);
        return ResponseEntity.ok().body(rating);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Rating> getRatingByHotelId(@PathVariable("hotelId") Long hotelId) {
        Rating rating = ratingService.getRatingByHotelId(hotelId);
        if (rating != null) {
            return ResponseEntity.ok().body(rating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@Valid @RequestBody Rating request) {
        // Convert CreateRatingRequest to Rating or use it directly
        Rating rating = new Rating();
        rating.setHotelId(request.getHotelId());
        rating.setStars(request.getStars());
        rating.setComment(request.getComment());

        Rating createdRating = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }
}

