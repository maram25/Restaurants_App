package com.Restaurants_App.android.Ui.AboutUs;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Restaurants_App.android.Models.AboutUs;
import com.Restaurants_App.android.R;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel mViewModel;
    TextView egy,Content,name;
    RelativeLayout Progress;
    ConstraintLayout Layout;
    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View root = inflater.inflate(R.layout.about_us_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(AboutUsViewModel.class);
        Content=root.findViewById(R.id.Content);
        name=root.findViewById(R.id.name);
        Progress = root.findViewById(R.id.Progress);
        Layout = root.findViewById(R.id.Layout);
      //  mViewModel.GetAboutAs();
       // StartProgress();
        mViewModel.Aboutus.observe(getViewLifecycleOwner(), new Observer<AboutUs>() {
            @Override
            public void onChanged(AboutUs aboutUs) {
                Content.setText(aboutUs.getData().get(0).getContent());
                name.setText(aboutUs.getData().get(0).getName());
                Log.e("Content", Content.toString() + "");
                EndProgress();
            }
        });



        return root;
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