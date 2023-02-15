package com.Restaurants_App.android.Ui.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.Restaurants_App.android.Models.PlaceHomeModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.Restaurant.RestaurantFragment;
import com.Restaurants_App.android.Utils;

public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.ViewHolder> {
    private final HomeFragment homeFragment;
    FragmentManager fragmentManager;
    PlaceHomeModel placeHomeModel;
    Context context;
    public ThirdAdapter(HomeFragment homeFragment, FragmentManager fragmentManager, Context context,
                        PlaceHomeModel placeHomeModel) {
        this.homeFragment = homeFragment;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.placeHomeModel = placeHomeModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurants, parent, false);
        ThirdAdapter.ViewHolder holder = new ThirdAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.e("getThird().size()", placeHomeModel.getThird().size() + "");
        Log.e("getName33", placeHomeModel.getThird().get(position).getName() + "");

        holder.RestaurantName.setText(placeHomeModel.getThird().get(position).getName());
        holder.underTittle.setText(placeHomeModel.getThird().get(position).getDescription());
       holder.ratingBar.setRating(placeHomeModel.getThird().get(position).getRate());
        holder.RatingNumber.setText(String.valueOf(placeHomeModel.getThird().get(position).getRate()));

        Glide.with(context)
                .asBitmap()
                .load(placeHomeModel.getThird().get(position).getCover())
                .into(holder.RestaurantImage);
        if (placeHomeModel.getThird().get(position).getCan_animal() == 1) {
            holder.animal.setVisibility(View.VISIBLE);
        }
        else
            holder.animal.setVisibility(View.GONE);

        if (placeHomeModel.getThird().get(position).getCan_delivery() == 1) {
            holder.delivery.setVisibility(View.VISIBLE);
        }
        else
            holder.delivery.setVisibility(View.GONE);

       // if (placeHomeModel.second.get(position).getType_id()==2)
        if (placeHomeModel.getThird().get(position).getType_id()==2)
        {
            holder.type.setBackgroundResource(R.drawable.rmini2);

        }
        else if (placeHomeModel.getThird().get(position).getType_id()==1)
        {
            holder.type.setBackgroundResource(R.drawable.rmini3);

        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(" Utils.Place_id ",  Utils.Place_id  + "");
                Utils.Place_id = placeHomeModel.getThird().get(position).getId();
              //  Utils.PlacesPhone = placeHomeModel.getThird().get(position).getPhone();

                Blackout(new RestaurantFragment());

            }
        });


    }

    @Override
    public int getItemCount() {
        return placeHomeModel.getThird().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView RestaurantName, underTittle, RatingNumber;
        ImageView RestaurantImage, animal, type,delivery;
        RatingBar ratingBar;
        ConstraintLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RestaurantImage = itemView.findViewById(R.id.restaurantsPhoto);
            underTittle = itemView.findViewById(R.id.underTittle);
            RestaurantName = itemView.findViewById(R.id.tv_sideTittle);
            RatingNumber = itemView.findViewById(R.id.tv_RatingNumber);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            animal = itemView.findViewById(R.id.animal);
            type = itemView.findViewById(R.id.type);
            item = itemView.findViewById(R.id.item);
            delivery = itemView.findViewById(R.id.delivery);
        }
    }

    private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
       // ft.replace(R.id.nav_host_fragment, fragment);
        ft.replace(R.id.home, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }
}
