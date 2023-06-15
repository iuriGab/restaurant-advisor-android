package com.example.restaurantadvisorsp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
    String url = "https://localhost:8080/restaurants";
    String json;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        getRaiz();

    }

    public void getRaiz(){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            json = response.body().string();
                            Gson gson = new Gson();
                            Restaurant[] restaurants = gson.fromJson(json, Restaurant[].class);

                            textView.setText(restaurants.toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }


}
