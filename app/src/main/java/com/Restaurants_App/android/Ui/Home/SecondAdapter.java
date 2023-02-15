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

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {
    private final HomeFragment homeFragment;
    FragmentManager fragmentManager;
    PlaceHomeModel placeHomeModel;
    Context context;
    public SecondAdapter(HomeFragment homeFragment, FragmentManager fragmentManager, Context context,
                         PlaceHomeModel placeHomeModel) {
        this.homeFragment = homeFragment;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.placeHomeModel = placeHomeModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        SecondAdapter.ViewHolder holder = new SecondAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


/*        Log.e("second.size()", placeHomeModel.second.size() + "");
        Log.e("getName22", placeHomeModel.second.get(position).getName() + "");*/

        holder.BrandName.setText(placeHomeModel.second.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(placeHomeModel.second.get(position).getLogo())
                .into(holder.BrandImage);


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(" Utils.Place_id ",  Utils.Place_id  + "");
                Utils.Place_id = placeHomeModel.second.get(position).getId();
               // Utils.PlacesPhone = placeHomeModel.second.get(position).getPhone();
                Blackout(new RestaurantFragment());

            }
        });

    }

    @Override
    public int getItemCount() {
        return placeHomeModel.second.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BrandName;
        ImageView BrandImage;
        RatingBar ratingBar;
        ConstraintLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BrandImage = itemView.findViewById(R.id.brandImage);
            BrandName = itemView.findViewById(R.id.brandName);

            item = itemView.findViewById(R.id.brand_item);
        }
    }

    private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
      //  ft.replace(R.id.nav_host_fragment, fragment);
        ft.replace(R.id.home, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }
}
