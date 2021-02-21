package io.javabrains.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
    
    @RequestMapping("/{moviesId}")
    public Rating getRating(@PathVariable("moviesId") String moviesId){
        return new Rating(moviesId,4);
    }
    @RequestMapping("/users/{userId}")
    public UserRating gUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings=Arrays.asList(
            new Rating("200",3),
            new Rating("100",4)
         );
        return new UserRating(ratings);
    }
}
