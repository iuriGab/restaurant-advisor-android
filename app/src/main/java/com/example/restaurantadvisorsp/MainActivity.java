package com.example.restaurantadvisorsp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Retrofit retrofit;
    private RecyclerView restaurants;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.117:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restaurants = findViewById(R.id.recycler_restaurants);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        restaurants.setLayoutManager(mLayoutManager);

        // Call the method with a callback
        getRestaurantsList(new RestaurantCallback() {
            @Override
            public void onSuccess(List<Restaurant> restaurantList) {
                // Create the adapter with the restaurant list
                adapter = new RestaurantAdapter(restaurantList);
                restaurants.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                // Display the error message
                textView.setText(errorMessage);
            }
        });
    }

    private void getRestaurantsList(final RestaurantCallback callback) {
        RestaurantService apiService = retrofit.create(RestaurantService.class);

        Call<List<Restaurant>> call = apiService.getRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful()) {
                    List<Restaurant> data = response.body();
                    callback.onSuccess(data); // Pass the restaurant list to the callback
                } else {
                    // Handle error response
                    int statusCode = response.code();
                    String errorMessage = "Error: " + statusCode;

                    // Check if the response body contains error details
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            errorMessage = errorBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    callback.onError(errorMessage); // Pass the error message to the callback
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                // Handle network or other errors
                Log.e("com.example.restaurant", t.getMessage());
                callback.onError("Error: " + t.getMessage()); // Pass the error message to the callback
            }
        });
    }

    // Callback interface for handling the API response
    interface RestaurantCallback {
        void onSuccess(List<Restaurant> restaurantList);

        void onError(String errorMessage);
    }
}