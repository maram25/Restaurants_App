package com.saudi_restaurants_and_cafes.android.Ui.Home;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaAspectRatio;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.saudi_restaurants_and_cafes.android.R;

import com.saudi_restaurants_and_cafes.android.MainActivity;
import com.saudi_restaurants_and_cafes.android.Models.PlaceHomeModel;
import com.saudi_restaurants_and_cafes.android.Models.TypesModel;
import com.saudi_restaurants_and_cafes.android.Ui.Filter.FilterFragment;
import com.saudi_restaurants_and_cafes.android.Ui.FilterHome.FilterHome;
import com.saudi_restaurants_and_cafes.android.Ui.Search.Search;
import com.saudi_restaurants_and_cafes.android.Ui.SelectTown.SelectTown;
import com.saudi_restaurants_and_cafes.android.Utils;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel mViewModel;
    MainActivity mainActivity;
    HomeFragment homeFragment = this;
    TextView All_Restaurants, Title1, Title2, Title3, Title4;
    private static final String TAG = "Home Fragment";
    ConstraintLayout SelectCity;
    ImageView filter, all_1, all_2, all_3, all_4, S;
    public InterstitialAd minterstitialAdd;

    TextView citye;
    RecyclerView Fourth_Restaurants, Family_Restaurants, maka_Restaurants, All_Brand;
    TextView Cafe, Restaurants;
    List<Integer> CitiesIds = new ArrayList<>();
    List<String> Cities = new ArrayList<>();

    AdView mAdView;

    RelativeLayout Progress;
    ConstraintLayout Layout;


    private static final String AD_UNIT_ID="ca-app-pub-3940256099942544/1033173712";
    //private static final String AD_UNIT_ID="ca-app-pub-9739571586618472/7412266837";


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart=sharedPreferences.getBoolean("firstStart",true);
        int CityID=sharedPreferences.getInt("CityID",Utils.SelectedCityId);
        String CityName=sharedPreferences.getString("CityName",Utils.SelectedCity);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Utils.SelectedCityId=sharedPreferences.getInt("CityID", 6);
        Utils.SelectedCity=sharedPreferences.getString("CityName", "الرياض");

        if(firstStart) {
            ((MainActivity) getActivity()).goToFragment(new SelectTown());
        }

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

       /* MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        ((MainActivity) getActivity()).loadAd();*/
        //loadAd();

        Definations(view);
        Actions();
        Observers();
        Setup();
        StartProgress();
        mViewModel.GetCities();
        StartProgress();
        mViewModel.GetType();
        StartProgress();
        mViewModel.GetPlaceHome();
        Log.e("GetPlaceHome", "GetPlaceHome");





        return view;
    }

    public void loadAd() {
      /*  AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(), AD_UNIT_ID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitialAd = interstitialAdd;
                Log.i(TAG, "onAdLoaded");
                Toast.makeText(getContext(), "onAdLoaded()", Toast.LENGTH_SHORT).show();
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        showInterstitial();
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        showInterstitial();

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
        });*/


       /* AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(),AD_UNIT_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        minterstitialAdd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        if (interstitialAd != null) {
                            interstitialAd.show(mainActivity);
                            Log.i("show", "show_ads");
                             EndProgress();

                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        minterstitialAdd = null;
                    }
                });*/
    }
    private void showInterstitial() {
        if (minterstitialAdd != null) {
            minterstitialAdd.show(mainActivity);
            Log.d("showwwwww", " shown.");

        } else {
            Toast.makeText(getContext(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void Setup() {
        citye.setText(Utils.SelectedCity);
        Log.e("SelectedCity", Utils.SelectedCity + "");
        // if (Utils.SelectedCity.isEmpty())
        if (citye.toString().isEmpty()) {
            ((MainActivity) getActivity()).goToFragment(new SelectTown());

        }

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    private void Definations(View view) {
        Progress = view.findViewById(R.id.Progress);
        Layout = view.findViewById(R.id.Layout);
        Family_Restaurants = view.findViewById(R.id.Family_Restaurants);
        maka_Restaurants = view.findViewById(R.id.maka_Restaurants);
        All_Brand = view.findViewById(R.id.All_Brand);
        Fourth_Restaurants = view.findViewById(R.id.fourth_Restaurants);
        // CitySpinner = view.findViewById(R.id.city);
        Cafe = view.findViewById(R.id.cafe);
        Restaurants = view.findViewById(R.id.Restaurantsf);
        citye = view.findViewById(R.id.citye);
        All_Restaurants = view.findViewById(R.id.All_Restaurants);
        SelectCity = view.findViewById(R.id.selcte_city);
        filter = view.findViewById(R.id.iv_arrange);
        Title1 = view.findViewById(R.id.title1);
        Title2 = view.findViewById(R.id.title2);
        Title3 = view.findViewById(R.id.title3);
        Title4 = view.findViewById(R.id.title4);
        all_1 = view.findViewById(R.id.all_1);
        all_2 = view.findViewById(R.id.all_2);
        all_3 = view.findViewById(R.id.all_3);
        all_4 = view.findViewById(R.id.all_4);
        S = view.findViewById(R.id.search);
        mAdView = view.findViewById(R.id.adView);
    }
    private void Observers() {
        mViewModel.Types.observe(getViewLifecycleOwner(), new Observer<List<TypesModel.Types>>() {
            @Override
            public void onChanged(List<TypesModel.Types> types) {
                for (int x = 0; x < types.size(); x++) {
                   /*  if(x==1)
                     {
                         Restaurants.setText(types.get(x).getName());
                     } if(x==2)
                     {
                         Cafe.setText(types.get(x).getName());
                     }*/
                }


                EndProgress();
            }
        });
        mViewModel.PlaceHome.observe(getViewLifecycleOwner(), new Observer<PlaceHomeModel>() {
            @Override
            public void onChanged(PlaceHomeModel placeHomeModel) {
              /*  if (PlaceHomeModel.First==0){
                    Toast.makeText(getContext(),("لا يوجد اماكن متاحه"),Toast.LENGTH_LONG).show();
                }*/
                Log.e("Title1", placeHomeModel.getTitles().get(0) + "");
                if(Utils.PlacesTypes ==1) {
                    Title1.setText(placeHomeModel.getTitles().get(0));
                }else
                {
                    Title1.setText("كافيهات عائلات");
                }
                Title2.setText(placeHomeModel.getTitles().get(1));
                Title3.setText("الاكثر بحثا");//for now
                Title4.setText(placeHomeModel.getTitles().get(3));

                RestaurantAdapter adapter2 = new RestaurantAdapter(homeFragment, getChildFragmentManager(), getContext(), placeHomeModel);
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                Family_Restaurants.setLayoutManager(layoutManager2);
                Family_Restaurants.setHasFixedSize(true);
                Family_Restaurants.setAdapter(adapter2);

                SecondAdapter adapter3 = new SecondAdapter(homeFragment, getChildFragmentManager(), getContext(), placeHomeModel);
                LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                All_Brand.setLayoutManager(layoutManager3);
                All_Brand.setHasFixedSize(true);
                All_Brand.setAdapter(adapter3);

                ThirdAdapter adapter4 = new ThirdAdapter(homeFragment, getChildFragmentManager(), getContext(), placeHomeModel);
                LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                maka_Restaurants.setLayoutManager(layoutManager4);
                maka_Restaurants.setHasFixedSize(true);
                maka_Restaurants.setAdapter(adapter4);

                FourthAdapter adapter5 = new FourthAdapter(homeFragment, getChildFragmentManager(), getContext(), placeHomeModel);
                LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                Fourth_Restaurants.setLayoutManager(layoutManager5);
                Fourth_Restaurants.setHasFixedSize(true);
                Fourth_Restaurants.setAdapter(adapter5);

                EndProgress();
              //  ((MainActivity) getActivity()).showInterstitial();
                Log.e("Adssssss",   "PlaceHome");

            }
        });

        mViewModel.NoInternet.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String Message) {
                Toast.makeText(getContext(), Message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Actions() {
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).goToFragment(new FilterFragment());

            }
        });
      /*  CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Utils.SelectedCityId = CitiesIds.get(position);
                Utils.SelectedCity= Cities.get(position);
                Log.e("SelectedCity", Utils.SelectedCity+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new Search());
            }
        });
        All_Restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    ((MainActivity) getActivity()).showInterstitial();
                Log.e("Adssssss",   "All_Restaurants");

                Utils.place_characteristics.clear();
                Utils.Cooktypes.clear();
                Utils.rate.clear();
                Utils.Can_children=0;
                Utils.Categories=0;
                Utils.Can_music=0;
                Utils.Can_famil=0;
                Utils.Can_animal=0;

                ((MainActivity) getActivity()).goToFragment(new FilterHome());
            }
        });
        all_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  ((MainActivity) getActivity()).showInterstitial();
                Log.e("Adssssss",   "all_1");

                Utils.place_characteristics.clear();
                Utils.Cooktypes.clear();
                Utils.rate.clear();
                Utils.Can_children=0;
                Utils.Categories=0;
                Utils.Can_music=0;
                Utils.Can_famil=0;
                Utils.Can_animal=0;
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });
        all_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity) getActivity()).showInterstitial();
                Utils.place_characteristics.clear();
                Utils.Cooktypes.clear();
                Utils.rate.clear();
                Utils.Can_children=0;
                Utils.Categories=0;
                Utils.Can_music=0;
                Utils.Can_famil=0;
                Utils.Can_animal=0;
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });
        all_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   ((MainActivity) getActivity()).showInterstitial();
                Utils.place_characteristics.clear();
                Utils.Cooktypes.clear();
                Utils.rate.clear();
                Utils.Can_children=0;
                Utils.Categories=0;
                Utils.Can_music=0;
                Utils.Can_famil=0;
                Utils.Can_animal=0;
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });
        all_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      ((MainActivity) getActivity()).showInterstitial();
                Utils.place_characteristics.clear();
                Utils.Cooktypes.clear();
                Utils.rate.clear();
                Utils.Can_children=0;
                Utils.Categories=0;
                Utils.Can_music=0;
                Utils.Can_famil=0;
                Utils.Can_animal=0;
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });
        citye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new SelectTown());
            }
        });
        Restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurants.setBackground(getResources().getDrawable(R.drawable.green_button));
                Restaurants.setTextColor(getResources().getColor(R.color.white));
                Cafe.setBackground(getResources().getDrawable(R.drawable.white_button));
                Cafe.setTextColor(getResources().getColor(R.color.green_dark2));
                Utils.PlacesTypes = 1;
                 All_Restaurants.setText("كل المطاعم");
                StartProgress();
                mViewModel.GetPlaceHome();
                Log.e(" Utils.PlacesTypes___", Utils.PlacesTypes + "");


            }
        });
        Cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cafe.setBackground(getResources().getDrawable(R.drawable.green_button));
                Cafe.setTextColor(getResources().getColor(R.color.white));
                Restaurants.setBackground(getResources().getDrawable(R.drawable.white_button));
                Restaurants.setTextColor(getResources().getColor(R.color.green_dark2));
                Utils.PlacesTypes = 2;
                StartProgress();
                All_Restaurants.setText("كل الكافيهات");

                mViewModel.GetPlaceHome();
                Log.e(" Utils.PlacesTypes___", Utils.PlacesTypes + "");

            }
        });
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