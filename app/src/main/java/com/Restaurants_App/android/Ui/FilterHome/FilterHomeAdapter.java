package com.Restaurants_App.android.Ui.FilterHome;

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
import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.Models.AllPlacesModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.Restaurant.RestaurantFragment;
import com.Restaurants_App.android.Utils;

import java.util.ArrayList;
import java.util.List;

public class FilterHomeAdapter extends RecyclerView.Adapter<FilterHomeAdapter.ViewHolder>{

    List<AllPlacesModel.Place> allPlacesModels = new ArrayList<>();
    List<AllFillterModel.Categories> allfiltet = new ArrayList<>();
    private final FilterHome filterHome;

    Context context;
    FragmentManager fragmentManager;
    private ArrayList<String> Restaurantname = new ArrayList<>();
    private ArrayList<String> RestaurantImage = new ArrayList<>();

    public FilterHomeAdapter(FilterHome filterHome, FragmentManager fragmentManager, Context context,
                                  List<AllPlacesModel.Place> allPlacesModels) {
        this.filterHome = filterHome;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allPlacesModels = allPlacesModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurants, parent, false);
        FilterHomeAdapter.ViewHolder holder = new FilterHomeAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Log.e("allPlacesModels.size()", allPlacesModels.size()+"");
        Log.e("getName()", allPlacesModels.get(position).getName()+"");

        // holder.RestaurantName.setText(Restaurantname.get(position));
        holder.RestaurantName.setText(allPlacesModels.get(position).getName());
        holder.underTittle.setText(allPlacesModels.get(position).getDescription());
        holder.ratingBar.setRating(allPlacesModels.get(position).getRate());
        holder.RatingNumber.setText(String.valueOf(allPlacesModels.get(position).getRate()));
        Glide.with(context)
                .asBitmap()
                .load(allPlacesModels.get(position).getCover())
                .into(holder.RestaurantImage);
        if (allPlacesModels.get(position).getCan_animal()==1)
        {
            holder.animal.setVisibility(View.VISIBLE);
        }
        else
            holder.animal.setVisibility(View.GONE);

        if (allPlacesModels.get(position).getType_id()==2)
        {
            holder.type.setBackgroundResource(R.drawable.rmini2);

        }
        else if (allPlacesModels.get(position).getType_id()==1)
        {
            holder.type.setBackgroundResource(R.drawable.rmini3);

        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.Place_id=allPlacesModels.get(position).getId();
                Blackout(new RestaurantFragment());

            }
        });

    }

    @Override
    public int getItemCount() {
        return allPlacesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView RestaurantName,underTittle,RatingNumber;
        ImageView RestaurantImage,animal,type;
        RatingBar ratingBar;
        ConstraintLayout item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RestaurantImage = itemView.findViewById(R.id.restaurantsPhoto);
            underTittle = itemView.findViewById(R.id.underTittle);
            RestaurantName = itemView.findViewById(R.id.tv_sideTittle);
            RatingNumber = itemView.findViewById(R.id.tv_RatingNumber);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            //  restaurantsPhoto = itemView.findViewById(R.id.restaurantsPhoto);
            animal = itemView.findViewById(R.id.animal);
            type = itemView.findViewById(R.id.type);
            item = itemView.findViewById(R.id.item);
        }
    }


    private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // ft.replace(R.id.nav_host_fragment, fragment);
        ft.replace(R.id.filter_home, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}
