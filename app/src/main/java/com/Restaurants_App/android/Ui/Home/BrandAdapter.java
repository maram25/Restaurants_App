package com.Restaurants_App.android.Ui.Home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.Restaurants_App.android.Models.BrandModel;
import com.Restaurants_App.android.R;

import java.util.ArrayList;
import java.util.List;


public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
   // private final HomeFragment homeFragment;

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> Brandname = new ArrayList<>();
    private ArrayList<String> BarandImage = new ArrayList<>();


    List<BrandModel> brandModels = new ArrayList<>();
    Context mcontext;
    FragmentManager fragmentManager;

 /*   public BrandAdapter(Context context, List<BrandModel> brandModels) {
        this.brandModels=brandModels;
        this.context=context;
    }*/

  /*  public BrandAdapter(HomeFragment homeFragment, FragmentManager fragmentManager, Context context, List<BrandModel> brandModels) {

        this.homeFragment = homeFragment;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.brandModels = brandModels;

    }*/


    public BrandAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        Brandname = names;
        BarandImage = imageUrls;
        mcontext = context;
    }

    @NonNull
    @Override
    public BrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.BrandName.setText(Brandname.get(position));

        Glide.with(mcontext)
                .asBitmap()
                .load(BarandImage.get(position))
                .into(holder.BrandImage);


    }

    @Override
    public int getItemCount() {
      //  return brandModels.size();
        return BarandImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BrandName;
        ImageView BrandImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BrandImage = itemView.findViewById(R.id.brandImage);
            BrandName = itemView.findViewById(R.id.brandName);

        }
    }
}
