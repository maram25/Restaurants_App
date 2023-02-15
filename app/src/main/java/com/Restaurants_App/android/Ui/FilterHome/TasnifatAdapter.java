package com.Restaurants_App.android.Ui.FilterHome;

import static com.Restaurants_App.android.R.drawable.dark_gray;
import static com.Restaurants_App.android.R.drawable.send;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Utils;

import java.util.List;

public class TasnifatAdapter extends RecyclerView.Adapter<TasnifatAdapter.ViewHolder> {


    private final FilterHome filterHome;
    FragmentManager fragmentManager;
    Context context;
    List<AllFillterModel.Categories> allFillterModel;
    int k=0;
    public TasnifatAdapter(FilterHome filterHome, FragmentManager fragmentManager, Context context,
                        List<AllFillterModel.Categories>  allFillterModel) {
        this.filterHome=filterHome;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allFillterModel=allFillterModel;

    }

    @NonNull
    @Override
    public TasnifatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasnif_item, parent, false);
        TasnifatAdapter.ViewHolder holder = new TasnifatAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TasnifatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.Name.setText(allFillterModel.get(position).getName());

        holder.Name.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Log.e("Categories", allFillterModel.get(position).getId()+ "");

                if(k==0) {
                    Utils.Categories=allFillterModel.get(position).getId();
                    holder.Name.setBackground(context.getDrawable(send));
                    k++;
                }
                else
                {
                    holder.Name.setBackground(context.getDrawable(dark_gray));
                    Utils.Categories=0;
                    k--;
                }
                //Utils.Categories=allFillterModel.get(position).getId();
               // holder.BrandName.setTextColor(Color.parseColor("#ffffff"));
               // holder.BrandName.setBackground(R.drawable.green_button);

                Blackout(new FilterHome());

            }
        });

    }

    @Override
    public int getItemCount() {
        return allFillterModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_Tasnif);


        }
    }

    private void Blackout(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //  ft.replace(R.id.nav_host_fragment, fragment);
        ft.replace(R.id.filter_home, fragment);

        ft.addToBackStack(null);
        ft.commit();
    }

}
