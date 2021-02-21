package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private RestTemplate restTemplate;
      
    @RequestMapping("/{userId}") 
    //@HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem>   getCatalog(@
                PathVariable String userId)
    {
        List<Rating> ratings=restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class).getUserRating();
        List<CatalogItem> catalogs= ratings.stream().map(rating->{
             Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
             return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
            //getForObject()
        }).collect(Collectors.toList());
        return catalogs;
    }

    public List<CatalogItem> getFallbackCatalog(){
        return Arrays.asList(new CatalogItem("No Movie","",0));
    }
}
