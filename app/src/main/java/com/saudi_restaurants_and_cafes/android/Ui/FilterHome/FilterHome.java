package com.saudi_restaurants_and_cafes.android.Ui.FilterHome;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.MediaAspectRatio;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.saudi_restaurants_and_cafes.android.MainActivity;
import com.saudi_restaurants_and_cafes.android.Models.AllFillterModel;
import com.saudi_restaurants_and_cafes.android.Models.AllPlacesModel;
import com.saudi_restaurants_and_cafes.android.R;
import com.saudi_restaurants_and_cafes.android.Ui.ArrangeBy.ArrangeBy;
import com.saudi_restaurants_and_cafes.android.Ui.Search.Search;
import com.saudi_restaurants_and_cafes.android.Ui.SelectTown.SelectTown;
import com.saudi_restaurants_and_cafes.android.Utils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterHome extends Fragment {


    MainActivity mainActivity;
    private FilterHomeViewModel mViewModel;
    FilterHome filterHome = this;
    RecyclerView location,FilterRec;
    TextView citye;
    ImageView Arrange,search;
    RelativeLayout Progress;
    ConstraintLayout Layout;
    private static final String TAG = "Home Filter Fragment";
    RecyclerView recyclerViewRestaurants;
    private ArrayList<String> Brandname = new ArrayList<>();
    private ArrayList<String> BarandImage = new ArrayList<>();
    List<AllPlacesModel.Place> place = new ArrayList<>();

    public InterstitialAd interstitialAdd;
   //private static final String AD_UNIT_ID="ca-app-pub-3940256099942544/1033173712";   //for test
      private static final String AD_UNIT_ID="ca-app-pub-9739571586618472/7412266837";     //for app


    public static FilterHome newInstance() {
        return new FilterHome();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.filter_home_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(FilterHomeViewModel.class);

        // loadAd();
        //   ((MainActivity) getActivity()).showInterstitial();
        //((MainActivity) requireActivity()).loadAd();

        Definations(view);
        Actions();
        Setup();
        Observers();
        StartProgress();
        mViewModel.AllPlace();
        StartProgress();
        mViewModel.GetFilter();

        return view;
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
/*
        InterstitialAd.load(getContext(), AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitialAd = interstitialAdd;
                Log.i(TAG, "onAdLoaded");
                Toast.makeText(getContext(), "onAdLoaded()", Toast.LENGTH_SHORT).show();
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        //showInterstitial();
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                       // showInterstitial();
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        showInterstitial();
                        interstitialAdd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.i(TAG, loadAdError.getMessage());
                //mainActivity.interstitialAd = null;
                String error = String.format("domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                Toast.makeText(getContext(), "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
*/

      /*  InterstitialAd.load(getContext(),AD_UNIT_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAdd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        if (interstitialAdd != null) {
                            interstitialAdd.show(mainActivity);
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAdd = null;
                    }
                });*/
    }
    private void showInterstitial() {
        if (interstitialAdd != null) {
            interstitialAdd.show(mainActivity);
            Log.d("showwwwww", " shown.");

        } else {
            Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }


    private void Definations(View view) {
        recyclerViewRestaurants = view.findViewById(R.id.Restaurantsf);
        FilterRec = view.findViewById(R.id.rc_filter);
        citye = view.findViewById(R.id.citye);
        Progress = view.findViewById(R.id.Progress);
        Layout = view.findViewById(R.id.Layout);
        Arrange=view.findViewById(R.id.iv_arrange);
        search=view.findViewById(R.id.search);


    }

    private void Actions() {

        citye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new SelectTown());
            }
        });

        Arrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new ArrangeBy());

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new Search());

            }
        });


    }

    private void Observers() {
        mViewModel.AllPlaces.observe(getViewLifecycleOwner(), new Observer<List<AllPlacesModel.Place>>() {
            @Override
            public void onChanged(List<AllPlacesModel.Place> places) {
                Log.e("allPlacesModels111", "___________________");
                Log.e("Utils.SelectedCityId", Utils.SelectedCityId+"");
                Log.e("Utils.PlacesTypes", Utils.PlacesTypes+"");
                Log.e("Utils.Categories", Utils.Categories+"");
                Log.e("Utils.rate",   Utils.rate+"");
                Log.e("Utils.Can_children",    Utils.Can_children+"");
                Log.e("Utils.Cooktypes",   Utils.Cooktypes+"");

                final FilterHomeAdapter adapter2 = new FilterHomeAdapter(filterHome, getActivity().getSupportFragmentManager(), getContext(), places);
                recyclerViewRestaurants.setLayoutManager(new GridLayoutManager(getContext(), 1));
                recyclerViewRestaurants.setAdapter(adapter2);
                EndProgress();
                if(adapter2.getItemCount()==0)
                {
                    Toast.makeText(getContext(), "عفوا لا يوجد مطاعم لهذا التصنيف", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mViewModel.filtetCategry.observe(getViewLifecycleOwner(), new Observer<List<AllFillterModel.Categories>>() {
            @Override
            public void onChanged(List<AllFillterModel.Categories> categories) {
                TasnifatAdapter adapter2 = new TasnifatAdapter(filterHome, getChildFragmentManager(), getContext(), categories);
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                FilterRec.setLayoutManager(layoutManager2);
                FilterRec.setHasFixedSize(true);
                FilterRec.setAdapter(adapter2);
                EndProgress();
            }
        });


    }

    private void Setup() {
        citye.setText(Utils.SelectedCity);
        if(Utils.PlacesTypes==2) {
            FilterRec.setVisibility(View.GONE);
        }
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