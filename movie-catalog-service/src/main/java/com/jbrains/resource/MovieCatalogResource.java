package com.jbrains.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.jbrains.models.CatalogItem;
import com.jbrains.models.Movie;
import com.jbrains.models.Rating;
import com.jbrains.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	    @Autowired
	    private RestTemplate restTemplate;

	   /* @Autowired
	    WebClient.Builder webClientBuilder;*/
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalogItems(@PathVariable("userId")String userId)
	{
		
		
        UserRating userRating=restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
		
		return userRating.getRatings().stream().map(ratings -> {
		    Movie movie=restTemplate.getForObject("http://movie-info-service/movies/" + ratings.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), movie.getDescription(), ratings.getRating());
			
		}).collect(Collectors.toList());
		//get all movie related ids
		
		//for each movie id,call the movie service get all movie details
		
		//all the data put together
		
		/*List<Rating> ratings=Arrays.asList(
				new Rating("11", 4),
				new Rating("22", 5),
				new Rating("33", 3),
				new Rating("44", 1)
				);
		
		return ratings.stream().map(rating -> {
			return new CatalogItem( "Atterantiki Daredi", "Power star movie",2);
		}).collect(Collectors.toList());*/
		
		
		
		/*return Collections.singletonList(
				new CatalogItem( "Atterantiki Daredi", "Power star movie",2)
				);*/
		
	}
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/
