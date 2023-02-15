package com.saudi_restaurants_and_cafes.android.Ui.Filter;

import static com.saudi_restaurants_and_cafes.android.R.drawable.send;
import static com.saudi_restaurants_and_cafes.android.R.drawable.titlerestaurant;
import com.saudi_restaurants_and_cafes.android.R;

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

import com.saudi_restaurants_and_cafes.android.Models.AllFillterModel;
import com.saudi_restaurants_and_cafes.android.R;
import com.saudi_restaurants_and_cafes.android.Utils;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private final FilterFragment filterFragment;
    FragmentManager fragmentManager;
    // AllFillterModel  allFillterModel;
    Context context;
    List<AllFillterModel.Cooking_types> allFillterModel1;
    int k=0;


    public FilterAdapter(FilterFragment filterFragment, FragmentManager fragmentManager, Context context,
                           List<AllFillterModel.Cooking_types>  allFillterModel1) {
        this.filterFragment=filterFragment;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allFillterModel1=allFillterModel1;



    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false);
        FilterAdapter.ViewHolder holder = new FilterAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.BrandName.setText(allFillterModel1.get(position).getName());
        holder.BrandName.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(k==0) {
                    holder.BrandName.setBackground(context.getDrawable(send));
                    holder.BrandName.setTextColor((R.color.white));
                    Utils.Cooktypes.add(allFillterModel1.get(position).getId());
                    Log.e("Utils.Cooktypes add", Utils.Cooktypes+ "");
                    k++;
                }
                else{
                    holder.BrandName.setBackground(context.getDrawable(titlerestaurant));
                    holder.BrandName.setTextColor((R.color.black));
                    Log.e("Utils.Cooktypes remove", Utils.Cooktypes+ "");
                    k--;
                }

             //  Utils.Cooktypes.add(allFillterModel1.get(position).getId());
                Log.e("Utils.Cooktypes", Utils.Cooktypes+ "");
               // Log.e("getId", allFillterModel1.get(position).getId()+ "");
            }
        });



    }

    @Override
    public int getItemCount() {
        return allFillterModel1.size();
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
