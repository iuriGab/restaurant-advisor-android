package com.example.restaurantadvisorsp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String json;
    TextView textView;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.117:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getApiData();

    }

    private void getApiData() {
        RestaurantService apiService = retrofit.create(RestaurantService.class);

        Call<List<Restaurant>> call = apiService.getRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful()) {
                    List<Restaurant> data = response.body();
                    textView.setText(data.get(0).getName());
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

                    // Display the error message
                    textView.setText(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                // Handle network or other errors
                Log.e("com.example.restaurant", t.getMessage());
                textView.setText("Error: " + t.getMessage());
            }
        });
    }
}