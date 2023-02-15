package com.Restaurants_App.android.Ui.PrivacyPolicy;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

public class Privacy_policyFragment extends Fragment {

    private PrivacyPolicyViewModel mViewModel;
    TextView egy,Content,name;
    RelativeLayout Progress;
    ConstraintLayout Layout;

    public static Privacy_policyFragment newInstance() {
        return new Privacy_policyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View root = inflater.inflate(R.layout.privacy_policy_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PrivacyPolicyViewModel.class);

        Content=root.findViewById(R.id.Content);
        name=root.findViewById(R.id.name);
        Progress = root.findViewById(R.id.Progress);
        Layout = root.findViewById(R.id.Layout);


        mViewModel.GetPrivacyPolicy();
        StartProgress();
        mViewModel.PrivacyPolicy.observe(getViewLifecycleOwner(), new Observer<AboutUs>() {
            @Override
            public void onChanged(AboutUs aboutUs) {
                Content.setText(aboutUs.getData().get(1).getContent());
                name.setText(aboutUs.getData().get(1).getName());
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