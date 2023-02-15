package com.Restaurants_App.android.Ui.SelectTown;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.Restaurants_App.android.Models.CitiesModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Utils;

import java.util.ArrayList;
import java.util.List;

public class SelectTown extends Fragment {

    private SelectTownViewModel mViewModel;
    RecyclerView CitiesList;

    List<Integer> CitiesIds = new ArrayList<>();
    List<String> Cities = new ArrayList<>();
    private ArrayList<String> count = new ArrayList<>();

    RelativeLayout Progress;
    ConstraintLayout Layout;
    public static SelectTown newInstance() {
        return new SelectTown();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.select_town_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(SelectTownViewModel.class);
        Definations(root);
        Actions();
        Observers();
        Setup();
        mViewModel.GetCities();
        StartProgress();
        return root;
    }

    private void Setup() {
        count.add("قاهره");
        count.add("الجيزه");
        count.add("دمياط");
        count.add("السويس");
        final SelectTownAdapter adapter = new SelectTownAdapter( getContext(),count,getActivity().getSupportFragmentManager());
        CitiesList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        CitiesList.setAdapter(adapter);
    }

    private void Observers() {
            mViewModel.Cities.observe(getViewLifecycleOwner(), new Observer<List<CitiesModel.Cities>>() {
            @Override
            public void onChanged(List<CitiesModel.Cities> cities) {
                CitiesIds = new ArrayList<>();
                Cities = new ArrayList<>();
                for (int i = 0; i < cities.size(); i++) {
                    Cities.add(cities.get(i).getName());
                    CitiesIds.add(cities.get(i).getId());

                }
                Utils.SelectedCityId = CitiesIds.get(0);
                Utils.SelectedCity = Cities.get(0);
              /*  final SelectTownAdapter adapter = new SelectTownAdapter( getContext(),getActivity().getSupportFragmentManager(), (ArrayList<CitiesModel.Cities>) cities);
                CitiesList.setLayoutManager(new GridLayoutManager(getContext(), 2));
                CitiesList.setAdapter(adapter);*/
                 EndProgress();
            }
        });

    }

    private void Actions() {
    }

    private void Definations(View root) {
        CitiesList = root.findViewById(R.id.cities_list);
        Progress = root.findViewById(R.id.Progress);
        Layout = root.findViewById(R.id.Layout);

    }

    public void StartProgress() {
        Progress.setVisibility(View.VISIBLE);
        Layout.setVisibility(View.GONE);
    }

    public void EndProgress() {
        Progress.setVisibility(View.GONE);
        Layout.setVisibility(View.VISIBLE);
    }

}