package com.cloud.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloud.demo.model.Movie;
import com.cloud.demo.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource { 
	
	@Value("${api.key}")
	private String apiKey;
	@Autowired
	RestTemplate restTemplate;
	Logger log= LoggerFactory.getLogger(MovieResource.class);
	
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		log.info("Movie Id is::"+movieId);
		log.info("https://api.themoviedb.org/3/movie/"+movieId +"?api_key="+apiKey);
		MovieSummary summary =restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key="+apiKey, MovieSummary.class);
		return new Movie(movieId,summary.getTitle(),summary.getOverview());
	}

}
