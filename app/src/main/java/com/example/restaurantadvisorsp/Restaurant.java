package com.example.restaurantadvisorsp;

import androidx.annotation.NonNull;

public class Restaurant {

    private String name;
    private String type;
    private String address;
    private String details;
    private String photos;
    private String hours;
    private String rating;
    private String cost;
    private boolean liked;

    // constructor and other methods
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", details='" + details + '\'' +
                ", photos='" + photos + '\'' +
                ", hours='" + hours + '\'' +
                ", rating='" + rating + '\'' +
                ", cost='" + cost + '\'' +
                ", liked=" + liked +
                '}';
    }

}
