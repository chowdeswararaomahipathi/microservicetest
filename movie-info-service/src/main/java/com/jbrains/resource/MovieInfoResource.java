package com.jbrains.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jbrains.models.Movie;
import com.jbrains.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	   /* @Value("${api.key}")
	    private String apiKey;*/

	    @Autowired
	    private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getmovieInfo(@PathVariable("movieId") String movieId)
	{
		/*return new Movie(movieId, "Test name");*/
		 /*MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  1, MovieSummary.class);
	     */  
		MovieSummary movieSummary=new MovieSummary();
		movieSummary.setTitle("Malli Rava");
		movieSummary.setOverview("One the most romantic and heart touching movis");
		 return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}

}
