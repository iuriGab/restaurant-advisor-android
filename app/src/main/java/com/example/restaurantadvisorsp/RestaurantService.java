package com.example.restaurantadvisorsp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {

    @GET("restaurants/findRestaurants")
    Call<List<Restaurant>> getRestaurants();

}
