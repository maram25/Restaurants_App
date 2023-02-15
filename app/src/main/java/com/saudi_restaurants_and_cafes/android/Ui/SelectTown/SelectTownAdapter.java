package com.saudi_restaurants_and_cafes.android.Ui.SelectTown;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.saudi_restaurants_and_cafes.android.Models.CitiesModel;
import com.saudi_restaurants_and_cafes.android.R;
import com.saudi_restaurants_and_cafes.android.Ui.Home.HomeFragment;
import com.saudi_restaurants_and_cafes.android.Utils;

import java.util.ArrayList;
import java.util.List;


public class SelectTownAdapter extends RecyclerView.Adapter<SelectTownAdapter.ViewHolder> {
	public SharedPreferences sp;
	public SharedPreferences.Editor Ed;

	List<CitiesModel.Cities> citiesList = new ArrayList<>();
	Context mcontext;
	FragmentManager fragmentManager;

	public SelectTownAdapter(Context context, FragmentManager fragmentManager, ArrayList<CitiesModel.Cities> Cities) {
		citiesList = Cities;
		mcontext = context;
		this.fragmentManager = fragmentManager;

	}


	@NonNull
	@Override
	public SelectTownAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_town, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}


	@Override
	public void onBindViewHolder(@NonNull SelectTownAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
		holder.CitieName.setText(citiesList.get(position).getName());

      /*  sp = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        Ed = sp.edit();
        Ed.putInt("SelectedCityId", citiesList.get(position).getId());

        Ed.commit();*/

		holder.CitieName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.SelectedCityId = citiesList.get(position).getId();
				Utils.SelectedCity = citiesList.get(position).getName();
				Log.e("SelectedCity", Utils.SelectedCity + "");
				Log.e("SelectedCityId", Utils.SelectedCityId + "");
				SharedPreferences sharedPreferences = mcontext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putBoolean("firstStart", false);
				editor.putInt("CityID", Utils.SelectedCityId);
				editor.putString("CityName", Utils.SelectedCity);
				editor.apply();

				Blackout(new HomeFragment());

			}
		});

	}

	@Override
	public int getItemCount() {
		return citiesList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView CitieName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			CitieName = itemView.findViewById(R.id.textView12);

		}

	}

	private void Blackout(Fragment fragment) {
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.nav_host_fragment, fragment);
		ft.addToBackStack(null);
		ft.commit();
	}
}
