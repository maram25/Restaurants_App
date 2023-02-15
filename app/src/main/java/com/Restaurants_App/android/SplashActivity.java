package com.Restaurants_App.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.Restaurants_App.android.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    ImageView companyLogo;
    LinearLayout x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        companyLogo = findViewById(R.id.companyLogo);
        Animation company_logo = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom_animation);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_animation);
        companyLogo.startAnimation(company_logo);
        SetAppLocale("en");
    }
    public SharedPreferences sp;
    public SharedPreferences.Editor Ed;
    String path = "";

    private void SetAppLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localeCode));
        } else {
            conf.locale = new Locale(localeCode);
        }
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences sp1 = getSharedPreferences("Login", MODE_PRIVATE);
        Ed = sp.edit();
        Ed.putString("Lang", localeCode);

        Ed.commit();
        resources.updateConfiguration(conf, dm);
        Utils.Lang = localeCode;
        Intent intent = new Intent(this, MainActivity.class);
        if (path != null) intent.putExtra("ShippingId",path);
        startActivity(intent);
        finish();
    }
}