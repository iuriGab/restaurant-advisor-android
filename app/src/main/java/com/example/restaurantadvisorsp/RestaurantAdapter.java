package com.example.restaurantadvisorsp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

        holder.name.setText(restaurant.getName());
        holder.type.setText(restaurant.getType());
        //holder.photos.setImageURI(Uri.parse(restaurant.getPhotos()));
        holder.rating.setText(restaurant.getRating());
        holder.cost.setText(restaurant.getCost());

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
        public final ImageView photos;
        public final TextView rating;
        public final TextView cost;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.textview_restaurant_name);
            type = view.findViewById(R.id.textview_restaurant_type);
            photos = view.findViewById(R.id.imageview_restaurant);
            rating = view.findViewById(R.id.textview_restaurant_ratingstring);
            cost = view.findViewById(R.id.textview_restaurant_cost);
        }

    }

}
