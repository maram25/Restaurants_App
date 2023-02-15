package com.Restaurants_App.android.Ui.Filter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.Restaurants_App.android.MainActivity;
import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.FilterHome.FilterHome;
import com.Restaurants_App.android.Utils;

public class FilterFragment extends Fragment {

    private FilterViewModel mViewModel1;
    FilterFragment filterFragment = this;
    RecyclerView R1, R2;
    RelativeLayout Progress;
    ConstraintLayout Layout;
    TextView result;
    ToggleButton none, one, two, there, four, five;
    CheckBox can_animal,can_chiled,family,muise;
    private static final String TAG = "Filter Fragment";


    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.filter_fragment, container, false);
        mViewModel1 = new ViewModelProvider(this).get(FilterViewModel.class);
        Definations(view);
        Actions();
        Setup();
        Observers();
       // StartProgress();

     //   mViewModel1.GetFilter();

        return view;
    }

    private void Observers() {
        mViewModel1.filter.observe(getViewLifecycleOwner(), new Observer<AllFillterModel>() {
            @Override
            public void onChanged(AllFillterModel allFillterModel) {
                FilterAdapter adapter = new FilterAdapter(filterFragment, getChildFragmentManager(), getContext(), allFillterModel.getCooking_types());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                R1.setLayoutManager(layoutManager);
               // R1.setLayoutManager(new GridLayoutManager(getContext(), 3));
                R1.setHasFixedSize(true);
                R1.setAdapter(adapter);




                FilterAdapter2 adapter2 = new FilterAdapter2(filterFragment, getChildFragmentManager(), getContext(), allFillterModel.getPlace_characteristics());
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                R2.setLayoutManager(layoutManager2);
              //  R2.setLayoutManager(new GridLayoutManager(getContext(), 3));
                R2.setHasFixedSize(true);
                R2.setAdapter(adapter2);

                EndProgress();
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
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });

        none.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    none.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    none.setTextColor(getResources().getColor(R.color.white));
                    Utils.rate.add(0);
                   // Utils.rate=0;
                    // The toggle is enabled
                } else {
                    none.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    none.setTextColor(getResources().getColor(R.color.black));
                    // The toggle is disabled
                   // int z=Utils.rate.indexOf(0);
                   // Utils.rate.remove(z);
                    // Utils.rate=null;
                }
            }
        });
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    one.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    one.setTextColor(getResources().getColor(R.color.white));
                    // The toggle is enabled
                    Utils.rate.add(1);
                    // Utils.rate=1;

                } else {
                    one.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    one.setTextColor(getResources().getColor(R.color.black));
                    // The toggle is disabled
                    //int z=Utils.rate.indexOf(1);
                    //Utils.rate.remove(z);
                    // Utils.rate=null;
                }
            }
        });
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    two.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    two.setTextColor(getResources().getColor(R.color.white));
                    Utils.rate.add(2);
                    // Utils.rate=2;

                    // The toggle is enabled
                } else {
                    two.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    two.setTextColor(getResources().getColor(R.color.black));
                    // The toggle is disabled
                   /// int z=Utils.rate.indexOf(2);
                    //Utils.rate.remove(z);
                    // Utils.rate=null;
                }
            }
        });
        there.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    there.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    there.setTextColor(getResources().getColor(R.color.white));
                    Utils.rate.add(3);
                    // Utils.rate=3;
                    // The toggle is enabled
                } else {
                    there.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    there.setTextColor(getResources().getColor(R.color.black));
                    // The toggle is disabled
                   // int z=Utils.rate.indexOf(3);
                    //Utils.rate.remove(z);
                    // Utils.rate=3;
                }
            }
        });
        four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    four.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    four.setTextColor(getResources().getColor(R.color.white));
                   Utils.rate.add(4);
                    // Utils.rate=4;

                    // The toggle is enabled
                } else {
                    four.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    four.setTextColor(getResources().getColor(R.color.black));
                    // The toggle is disabled
                    //int z=Utils.rate.indexOf(4);
                    //Utils.rate.remove(z);
                    // Utils.rate=null;

                }
            }
        });
        five.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    five.setBackground(getResources().getDrawable(R.drawable.rate_done));
                    five.setTextColor(getResources().getColor(R.color.white));
                    Utils.rate.add(5);
                    // Utils.rate=5;
                    // The toggle is enabled
                } else {
                    five.setBackground(getResources().getDrawable(R.drawable.roundborder));
                    five.setTextColor(getResources().getColor(R.color.black));
                   // Utils.rate.remove(5);
                    //int z=Utils.rate.indexOf(5);
                   // Utils.rate.remove(z);
                    // Utils.rate=null;
                    // The toggle is disabled
                }
            }
        });
        can_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (can_animal.isChecked()) {
                    Log.e("Add", "add");
                    Utils.Can_animal=1;
                } else {
                    Log.e("remove", "remove");
                    Utils.Can_animal=0;
                }
            }
        }); can_chiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (can_chiled.isChecked()) {
                    Log.e("Add", "add");
                    Utils.Can_children=1;
                } else {
                    Log.e("remove", "remove");
                    Utils.Can_children=0;
                }
            }
        }); family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (family.isChecked()) {
                    Log.e("Add", "add");
                    Utils.Can_famil=1;
                } else {
                    Log.e("remove", "remove");
                    Utils.Can_famil=0;
                }
            }
        }); muise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (muise.isChecked()) {
                    Log.e("Add", "add");
                    Utils.Can_music=1;
                } else {
                    Log.e("remove", "remove");
                    Utils.Can_music=0;
                }
            }
        });

    }

    private void Definations(View view) {
        Progress = view.findViewById(R.id.Progress);
        Layout = view.findViewById(R.id.Layout);
        R1 = view.findViewById(R.id.R1);
        R2 = view.findViewById(R.id.R2);
        result = view.findViewById(R.id.tv_showRes);
        none = view.findViewById(R.id.toggleButton12);
        one = view.findViewById(R.id.toggleButton11);
        two = view.findViewById(R.id.toggleButton10);
        there = view.findViewById(R.id.toggleButton8);
        four = view.findViewById(R.id.toggleButton14);
        five = view.findViewById(R.id.toggleButton13);
        can_animal = view.findViewById(R.id.checkBox9);
        can_chiled= view.findViewById(R.id.checkBox7);
        family= view.findViewById(R.id.checkBox);
        muise= view.findViewById(R.id.checkBox8);

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