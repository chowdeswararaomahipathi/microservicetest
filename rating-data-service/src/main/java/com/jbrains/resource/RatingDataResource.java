package com.jbrains.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbrains.models.Rating;
import com.jbrains.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId")String movieId)
	{
		return new Rating(movieId, 4);
	}
	
	
	@RequestMapping("/user/{userId}")
	public UserRating getUserRatings(@PathVariable("userId")String userId)
	{
		UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
	}
	

}
