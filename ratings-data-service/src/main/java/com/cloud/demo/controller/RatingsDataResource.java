package com.cloud.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.demo.model.Rating;
import com.cloud.demo.model.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings= Arrays.asList(
				new Rating("550",5),
				new Rating("551",3));
		
		UserRating userRatings= new  UserRating();
		userRatings.setUserRatings(ratings);
		return userRatings;
	}
}
