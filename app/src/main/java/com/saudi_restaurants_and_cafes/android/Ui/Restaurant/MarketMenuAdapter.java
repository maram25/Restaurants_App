package com.saudi_restaurants_and_cafes.android.Ui.Restaurant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.saudi_restaurants_and_cafes.android.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saudi_restaurants_and_cafes.android.Models.PlacesDetailsModel;

public class MarketMenuAdapter extends RecyclerView.Adapter<MarketMenuAdapter.MarketMenu> {

    //private List<PlacesDetailsModel.Data> menu_images = new ArrayList<>();
    private final PlacesDetailsModel.Data menu_images ;
    RestaurantFragment restaurantFragment;
    Context context;

    public MarketMenuAdapter(RestaurantFragment restaurantFragment, Context context,  PlacesDetailsModel.Data list) {
        this.menu_images = list;
        this.context = context;
        this.restaurantFragment = restaurantFragment;
    }

    @NonNull
    @Override
    public MarketMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_menu_images, parent, false);
        MarketMenu holder = new MarketMenu(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MarketMenu holder, @SuppressLint("RecyclerView") final int position) {

        Glide.with(context)
                .asBitmap()
                .load(menu_images.menu_image_links.get(position))
                .into(holder.menu_image);
        Log.e("menu_image", menu_images.menu_image_links.size() + "");

        if (menu_images.menu_image_links.get(position)==null)
        {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.noimage)
                    .into(holder.menu_image);
        }

    }

    @Override
    public int getItemCount() {
        return menu_images.menu_image_links.size();
    }

    public class MarketMenu extends RecyclerView.ViewHolder {

        ImageView menu_image;

        public MarketMenu(View itemView) {
            super(itemView);
            menu_image = (ImageView) itemView.findViewById(R.id.menu_image);

        }


    }

}
