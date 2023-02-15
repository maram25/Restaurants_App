package com.saudi_restaurants_and_cafes.android.Ui.GetHelp;

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
import android.widget.EditText;
import android.widget.TextView;

import com.saudi_restaurants_and_cafes.android.MainActivity;
import com.saudi_restaurants_and_cafes.android.R;
import com.saudi_restaurants_and_cafes.android.Ui.Home.HomeFragment;
import com.saudi_restaurants_and_cafes.android.Utils;

public class GetHelp extends Fragment {

    private GetHelpViewModel mViewModel;
     EditText Name,Phonenumber,Help;
     TextView Sendhelp;
    public static GetHelp newInstance() {
        return new GetHelp();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View root= inflater.inflate(R.layout.get_help_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(GetHelpViewModel.class);

        Definations(root);
        Actions();
        Observers();

        Phonenumber.setText(Utils.PhoneNumber);
        Name.setText(Utils.Name);

        return  root;
    }

    private void Actions() {
        Sendhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.SendHelpForm(Name.getText().toString(),Phonenumber.getText().toString(),Help.getText().toString());
                Log.e("Name", Name + "");
                Log.e("Phonenumber", Name + "");
                Log.e("Help", Name + "");

                Sendhelp.setEnabled(false);
                Sendhelp.setVisibility(View.GONE); // hide it


            }
        });
    }

    private void Observers() {

        mViewModel.Help.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ((MainActivity) getActivity()).goToFragment(new HomeFragment());

            }
        });


    }

    private void Definations(View root) {
        Name = root.findViewById(R.id.iv_name);
        Phonenumber = root.findViewById(R.id.iv_phonenumber);
        Help = root.findViewById(R.id.iv_help);
        Sendhelp = root.findViewById(R.id.tv_sendhelp);

    }


}