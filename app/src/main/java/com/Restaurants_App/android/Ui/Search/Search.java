package com.Restaurants_App.android.Ui.Search;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.Restaurants_App.android.MainActivity;
import com.Restaurants_App.android.Models.AllPlacesModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.Home.HomeFragment;
import com.Restaurants_App.android.Utils;

import java.util.List;

public class Search extends Fragment {

    private SearchViewModel mViewModel;
    Search search = this;
    RecyclerView  R0;
    EditText E0;
    TextView T0;

    //ConstraintLayout Layout;
    private static final String TAG ="Search Fragment";
   // private ArrayList<String> Brandname = new ArrayList<>();
  //  private ArrayList<String> BarandImage = new ArrayList<>();
   // List<AllPlacesModel.Place> place = new ArrayList<>();


    public static Search newInstance() {
        return new Search();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        Definations(view);
        Actions();
        Setup();
        Observers();

        //StartProgress();
        mViewModel.AllPlace();

        return view;
    }




    private void Observers() {

        Log.e("allPlacesModels", "___________________");
        mViewModel.AllPlaces.observe(getViewLifecycleOwner(), new Observer<List<AllPlacesModel.Place>>() {
            @Override
            public void onChanged(List<AllPlacesModel.Place> places) {
                Log.e("allPlacesModels111", "___________________");

                final SearchAdapter adapter2 = new SearchAdapter(search, getActivity().getSupportFragmentManager(), getContext(), places);
               // E0.setText("star");
                //adapter2.getFilter().filter(E0.getText());
                R0.setLayoutManager(new GridLayoutManager(getContext(), 1));
                R0.setAdapter(adapter2);

                //EndProgress();
            }
        });


    }

    private void Setup() {
        Utils.place_characteristics.clear();
        Utils.Cooktypes.clear();
        Utils.rate.clear();
        Utils.Can_children=0;
        Utils.Categories=0;
        Utils.Can_music=0;
        Utils.Can_famil=0;
        Utils.Can_animal=0;
    }

    private void Actions() {
        E0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
            E0.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Utils.search= String.valueOf(E0.getText());
                    mViewModel.AllPlace();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            T0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(E0.getText().toString().isEmpty())
                    {
                        ((MainActivity) getActivity()).goToFragment(new HomeFragment());


                    }
                    else{

                        E0.getText().clear();
                    }
                }
            });

    }

    private void Definations(View view) {
        R0=view.findViewById(R.id.rv_R0);
        E0=view.findViewById(R.id.et_search);
        T0=view.findViewById(R.id.tv_search);


    }




}