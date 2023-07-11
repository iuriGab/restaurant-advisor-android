package com.example.restaurantadvisorsp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{
    private List<Restaurant> restaurants;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        // method to define how many stars will show in the stars
        float rating = Float.parseFloat(restaurant.getRating());
        int integerRating = (int) rating;
        String starRating = "";
        for (int i = 0; i < integerRating; i++) {
            starRating += "*";
        }

        // Set random number of users
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1000; // Generate random number between 1000 and 2000

        // Load image from URL and set it in the ImageView
        Picasso.get().load(restaurant.getPhotos())
                .fit()
                .centerCrop()
                .into(holder.photos);
        holder.name.setText(restaurant.getName());
        holder.type.setText(restaurant.getType());
        holder.rating.setText("rating: " + restaurant.getRating());
        // Set initial value for ratingUsers
        final int[] ratingUsersValue = {randomNumber};
        holder.ratingUsers.setText("(" + ratingUsersValue[0] + ")");
        holder.cost.setText(restaurant.getCost());
        holder.stars.setText(starRating);

        holder.photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the context from the holder's view
                Context context = v.getContext();

                // Create an Intent to open the new Activity
                Intent intent = new Intent(context, RestaurantDetail.class);
                context.startActivity(intent);
            }
        });
        // Set up the OnClickListener for the like button
        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            private boolean isLiked = false;
            private int clickCount = 0;

            @Override
            public void onClick(View v) {
                isLiked = !isLiked;
                int color = (isLiked) ? Color.RED : Color.WHITE;
                holder.buttonLike.setColorFilter(color, PorterDuff.Mode.SRC_IN);

                // Increment or decrement ratingUsers value based on like button state
                if (color == Color.RED) {
                    clickCount++;
                } else if (color == Color.WHITE) {
                    clickCount--;
                }

                ratingUsersValue[0] += clickCount;
                holder.ratingUsers.setText("(" + ratingUsersValue[0] + ")");
            }
        });

    }

    @Override
    public int getItemCount() {
        if (restaurants != null) {
            return restaurants.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView type;
        public final TextView address;
        public final TextView details;
        public final ImageView buttonLike;
        public final ImageView photos;
        public final TextView hours;
        public final TextView rating;
        public final TextView ratingUsers;
        public final TextView cost;
        public final TextView stars;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.textview_restaurant_name);
            type = view.findViewById(R.id.textview_restaurant_type);
            address = view.findViewById(R.id.textview_restaurant_street);
            details = view.findViewById(R.id.textview_restaurant_detail);
            buttonLike = view.findViewById(R.id.imageview_restaurant_button_like);
            photos = view.findViewById(R.id.imageview_restaurant);
            hours = view.findViewById(R.id.textview_restaurant_hour);
            rating = view.findViewById(R.id.textview_restaurant_ratingstring);
            ratingUsers = view.findViewById(R.id.textview_restaurant_ratingusers);
            cost = view.findViewById(R.id.textview_restaurant_cost);
            stars = view.findViewById(R.id.textview_restaurant_stars);
        }

    }

}
