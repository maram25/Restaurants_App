package com.Restaurants_App.android.Ui.ArrangeBy;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.Restaurants_App.android.MainActivity;
import com.Restaurants_App.android.R;

import com.Restaurants_App.android.Ui.FilterHome.FilterHome;
import com.Restaurants_App.android.Utils;

public class ArrangeBy extends Fragment {

    private ArrangeByViewModel mViewModel;
    TextView Arrange;
    RadioButton r1,r2;
    RadioGroup radioGroup;
    String sort="null";

    public static ArrangeBy newInstance() {
        return new ArrangeBy();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        View view = inflater.inflate(R.layout.arrange_by_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ArrangeByViewModel.class);
        //checkButton(view);
        Definations(view);
        Setup();
        Actions();
        Observers();


     //   mViewModel.AllPlace();
        return view;

    }

    private void Observers() {

    }

    private void Setup() {

    }

    private void Actions() {

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                switch(checkedId){
                    case R.id.rate: //1st option selected
                        sort="rating";

                        break;
                    case R.id.search: //2nd option selected
                        sort="search";

                        break;



                }
            }
        });
        /*if (r1.isChecked())
        {
            sort="rating";
            Log.e("rating",  "rating");
        }

        else if (r2.isChecked())
        {
            sort="search";
            Log.e("search",  "search");

        }*/
    /*    else
            sort="null";*/

        Arrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Utils.sort", Utils.sort_by+ "");
                Log.e("sort", sort+ "");
                Utils.sort_by=sort.toString();
                ((MainActivity) getActivity()).goToFragment(new FilterHome());

            }
        });
    }

    private void Definations(View view) {
        r1=view.findViewById(R.id.rate);
        r2=view.findViewById(R.id.search);
        radioGroup=view.findViewById(R.id.radioGroup);
        Arrange = view.findViewById(R.id.tv_buttomArrange);
       // checkButton(radioGroup);

    }
   /* private void checkButton(View view)
    {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=view.findViewById(radioId);


    }

*/

}