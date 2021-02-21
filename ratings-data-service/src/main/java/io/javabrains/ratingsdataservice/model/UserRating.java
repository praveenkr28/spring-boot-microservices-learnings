package io.javabrains.ratingsdataservice.model;

import java.util.List;

public class UserRating {

    private List<Rating> userRating;

    public UserRating() {
    }

    public UserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

    public List<Rating> getUserRating() {
        return this.userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }

}

    
