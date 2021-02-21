package io.javabrains.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.movieinfoservice.model.Movie;
import io.javabrains.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    
    //@Value("{api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId){
        apiKey="c8ed1aed6f61f611d3b39c5ac7b7ada8";
        MovieSummary movieSummary= restTemplate.getForObject("http://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey+"&language=en-US", MovieSummary.class);
        //MovieSummary movieSummary= restTemplate.getForObject("http://api.themoviedb.org/3/movie/550?api_key=c8ed1aed6f61f611d3b39c5ac7b7ada8", MovieSummary.class);
        return new Movie(movieId,movieSummary.getTitle() ,movieSummary.getOverview());
    }
}
