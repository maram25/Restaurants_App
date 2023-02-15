package com.Restaurants_App.android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import androidx.navigation.ui.NavigationUI;

import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.Restaurants_App.android.Ui.AboutUs.AboutUsFragment;
import com.Restaurants_App.android.Ui.GetHelp.GetHelp;
import com.Restaurants_App.android.Ui.Home.HomeFragment;
import com.Restaurants_App.android.Ui.PrivacyPolicy.Privacy_policyFragment;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;


public class
MainActivity extends AppCompatActivity {
    public SharedPreferences sp;
    public SharedPreferences.Editor Ed;
    ImageView Menu;
    DrawerLayout drawer;
    private ViewPager mViewPager;
    private AppBarConfiguration mAppBarConfiguration;
   public InterstitialAd minterstitialAdd;
  //  private AdManagerInterstitialAd interstitialAd;
  // private static final String AD_UNIT_ID="ca-app-pub-3940256099942544/1033173712";    // test
    private static final String AD_UNIT_ID="ca-app-pub-9739571586618472/7412266837";     //for app
    private static final String TAG = "MyActivity";
    private boolean adIsLoading;
     MobileAds mobileAds;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setShowWhenLocked(true);
        setTurnScreenOn(true);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {@Override public void onInitializationComplete(InitializationStatus status) {}});
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        }
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //  getSupportActionBar().hide();
      //  SetAppLocale("en");
        Definations();
        Actions();
        Observers();
       // loadAd();

        sp = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        Ed = sp.edit();
        Ed.putString("Lang", "en");
        Ed.commit();

    }



    public void loadAd() {

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,AD_UNIT_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        minterstitialAdd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        if (interstitialAd != null) {
                            interstitialAd.show(MainActivity.this);
                            Log.i("show", "show_ads");

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
                });
    }

    public void showInterstitial() {
        if (minterstitialAdd != null) {
            minterstitialAdd.show(this);
        } else {
         //   Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    private void Observers() {
    }
    private void Definations() {
        Menu = findViewById(R.id.Menu);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void Actions() {
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  drawer.open();
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                }
                else {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

    }

    public boolean onItemSelected(@NonNull View item) {
    //    drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        Log.e("clk", "clkd");
        switch (item.getId()) {
            case R.id.Home:
                goToFragment(new HomeFragment());
                break;
            case R.id.About_app:
               goToFragment(new AboutUsFragment());
                break;
            case R.id.Privacy:

                goToFragment(new Privacy_policyFragment());
                break;

            case R.id.Help:
                goToFragment(new GetHelp());
                break;

            case R.id.share:
                Intent x = new Intent(Intent.ACTION_SEND);
                x.setType("text/plain");
                x.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
/*
                x.putExtra(Intent.EXTRA_TEXT, "https://www.egydesigner.com/");
*/
                x.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" +  BuildConfig.APPLICATION_ID);

                startActivity(Intent.createChooser(x, "Share URL"));

                break;
        }
        return false;
    }
    public void goToFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.nav_host_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public void onBackPressed() {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count == 1) {
              //  finish();
                this.moveTaskToBack(true);

                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
            }
    }

}