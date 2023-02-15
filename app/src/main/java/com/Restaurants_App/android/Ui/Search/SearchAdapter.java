package com.Restaurants_App.android.Ui.Search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.Restaurants_App.android.Models.AllPlacesModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.Restaurant.RestaurantFragment;
import com.Restaurants_App.android.Utils;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>  {

    List<AllPlacesModel.Place> allPlacesModels = new ArrayList<>();
   // List<AllPlacesModel.Place> allPlacesModelsF = new ArrayList<>();

    private final Search search;
    Context context;
    FragmentManager fragmentManager;
   // private ArrayList<String> Restaurantname = new ArrayList<>();
   // private ArrayList<String> RestaurantImage = new ArrayList<>();

    public SearchAdapter(Search search, FragmentManager fragmentManager, Context context,
                           List<AllPlacesModel.Place> allPlacesModels)
    {
        this.search = search;
        this.fragmentManager=fragmentManager;
        this.context=context;
        this.allPlacesModels=allPlacesModels;
       //this.allPlacesModelsF=allPlacesModelf;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        SearchAdapter.ViewHolder holder = new SearchAdapter.ViewHolder(view);
        return holder;

    }
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

       // Log.e("allPlacesModels.size()", allPlacesModels.size()+"");
      //  Log.e("getName()", allPlacesModels.get(position).getName()+"");

        // holder.RestaurantName.setText(Restaurantname.get(position));
        holder.RestaurantName.setText(allPlacesModels.get(position).getName());
       // holder.underTittle.setText(allPlacesModels.get(position).getDescription());

        Glide.with(context)
                .asBitmap()
                .load(allPlacesModels.get(position).getCover())
                .into(holder.RestaurantImage);


// to dooooo
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
/*
    @Override
    public Filter getFilter() {

        return filter;
    }
    Filter filter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<AllPlacesModel.Place> allPlacesModelsFilerd = new ArrayList<>();

            if(charSequence.toString().isEmpty())
            {
                        allPlacesModelsFilerd.addAll(allPlacesModels);
            }else {
                for(AllPlacesModel.Place name : allPlacesModels)
                {
                    if(name.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                                   allPlacesModelsFilerd.add(name);
                    }

                }


            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=allPlacesModelsFilerd;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            allPlacesModels.clear();
            allPlacesModels.addAll((Collection<? extends AllPlacesModel.Place>) filterResults.values);
            notifyDataSetChanged();

        }
    };

*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView RestaurantName;
       // TextView underTittle;
        ImageView RestaurantImage;
        ConstraintLayout item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RestaurantImage = itemView.findViewById(R.id.iv_restPhoto);
          //  underTittle = itemView.findViewById(R.id.tv_restName);
            RestaurantName = itemView.findViewById(R.id.tv_restName);
            item = itemView.findViewById(R.id.item_search);



        }
    }
    private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.search_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}
