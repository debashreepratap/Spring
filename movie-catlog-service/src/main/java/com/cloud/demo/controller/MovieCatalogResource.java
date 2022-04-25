package com.cloud.demo.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cloud.demo.model.CatalogItem;
import com.cloud.demo.model.Movie;
import com.cloud.demo.model.Rating;
import com.cloud.demo.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@RequestMapping("{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings= restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId, UserRating.class);
		
		/*
		 * List<Rating> ratings= Arrays.asList( new Rating("123",5), new
		 * Rating("456",4));
		 */
		return ratings.getUserRatings().stream().map(rating -> { 
			           
		         Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
			/*
			  Movie movie = webClientBuilder.build()
			  .get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
			  .retrieve().bodyToMono(Movie.class).block();
			 */       
			    return new CatalogItem( movie.getName(),"Desc",rating.getRating());
		
		}).collect(Collectors.toList());
	}
}
