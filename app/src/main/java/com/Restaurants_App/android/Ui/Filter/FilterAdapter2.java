package com.Restaurants_App.android.Ui.Filter;

import static com.Restaurants_App.android.R.drawable.send;
import static com.Restaurants_App.android.R.drawable.titlerestaurant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Utils;

import java.util.List;

public class FilterAdapter2 extends RecyclerView.Adapter<FilterAdapter2.ViewHolder> {

    private final FilterFragment filterFragment;
    FragmentManager fragmentManager;
    // AllFillterModel  allFillterModel;
    Context context;
    List<AllFillterModel.Place_characteristics> allFillterModel2;
    int k=0;


    public FilterAdapter2(FilterFragment filterFragment, FragmentManager fragmentManager, Context context,
                         List<AllFillterModel.Place_characteristics>  allFillterModel2) {
        this.filterFragment=filterFragment;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allFillterModel2=allFillterModel2;



    }

    @NonNull
    @Override
    public FilterAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false);
        FilterAdapter2.ViewHolder holder = new FilterAdapter2.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.BrandName.setText(allFillterModel2.get(position).getName());
        holder.BrandName.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(k==0) {
                    holder.BrandName.setBackground(context.getDrawable(send));
                    holder.BrandName.setTextColor((R.color.white));
                    k++;
                }
                else{
                    holder.BrandName.setBackground(context.getDrawable(titlerestaurant));
                    holder.BrandName.setTextColor((R.color.black));
                    k--;

                }

             Utils.place_characteristics.add(allFillterModel2.get(position).getId());
                Log.e("place_characteristics", Utils.place_characteristics+ "");
                Log.e("getId", allFillterModel2.get(position).getId()+ "");

            }
        });

    }

    @Override
    public int getItemCount() {
        return allFillterModel2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BrandName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BrandName = itemView.findViewById(R.id.tv_Filter);


        }
    }

   /* private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //  ft.replace(R.id.nav_host_fragment, fragment);
        ft.replace(R.id.home, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }*/
}
