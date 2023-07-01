package com.example.restaurantadvisorsp;

public class Restaurant {

    private String name;
    private String type;
    private String photos;
    private String rating;
    private String cost;
    private boolean liked;

    // constructor and other methods
    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", photos='" + photos + '\'' +
                ", rating='" + rating + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
