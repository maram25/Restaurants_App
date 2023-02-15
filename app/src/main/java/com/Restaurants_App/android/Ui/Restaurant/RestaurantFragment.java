package com.Restaurants_App.android.Ui.Restaurant;

import static android.content.Context.MODE_PRIVATE;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdSize;
import com.Restaurants_App.android.MainActivity;
import com.Restaurants_App.android.Models.PlacesDetailsModel;
import com.Restaurants_App.android.R;
import com.Restaurants_App.android.Ui.Home.HomeFragment;
import com.Restaurants_App.android.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RestaurantFragment extends Fragment {

    private RestaurantViewModel mViewModel;
    RestaurantFragment restaurantFragment;
    LinearLayout Add_rate;
    TextView Sendrate, Title, underTittle, Location, RatingNumber, time, time_stute, PlaceName,Categrey;
    EditText Phonenumber, Name, Note;
    ConstraintLayout rate_popup, layout_menue;
    RatingBar rate;
    ShapeableImageView Cover, Logo;
    RelativeLayout Progress;
    ConstraintLayout Layout;
    LinearLayout menu;
    RecyclerView Menu_images;

    RatingBar ratingBar;
    ImageView Calling, iv_ficon, insta_icon, you_icon, twitter_icon, animal, type, delivery, close,closeR,closeRest,can_music,iv_wicon;
    String PhonePlace;
    String facbook, instagram, twitter, youtupe,website;
    PackageManager pm;
    Context context;
    Activity activity;
    private static final int REQUEST_CALL = 1;

    String OpenTIme, CloseTime;
    public static final String inputFormat = "HH:mm";
    Date date;
    Date dateCompareOne;
    Date dateCompareTwo;
      String  Name_olde,Phone_Old;

    AdView mAdView;


    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);

    public SharedPreferences sp ,pref;
    public SharedPreferences.Editor Ed;

   // SharedPreferences sharedPreferences;

    public static RestaurantFragment newInstance() {
        return new RestaurantFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View root = inflater.inflate(R.layout.restaurant_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
       // sharedPreferences= activity.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Definations(root);
        Actions();
        Observers();
        setup();
        StartProgress();
        mViewModel.GetPlaceDetail();
        pref = this.getActivity().getSharedPreferences("info", MODE_PRIVATE);
        Log.e("Name", Utils.Name + "");
        Log.e("Name_olde", Name_olde + "");
        Log.e("Phone_Old", Phone_Old + "");


        return root;
    }


    private void compareDates() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        date = parseDate(hour + ":" + minute);
        dateCompareOne = parseDate(OpenTIme);
        dateCompareTwo = parseDate(CloseTime);

        if (dateCompareOne.before(date)) {
            time_stute.setText("hhhhhhhhhhhhhhh");
        } else if (dateCompareTwo.after(date)) {
            time_stute.setText("pppppppppppp");
        }

    }

    private Date parseDate(String date) {
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
    private void Observers() {
        mViewModel.PlaceDetails.observe(getViewLifecycleOwner(), new Observer<PlacesDetailsModel>() {
            @Override
            public void onChanged(PlacesDetailsModel placesDetailsModel) {
                Log.e("Title", Title + "");
                Title.setText(placesDetailsModel.getData().getName());
                PlaceName.setText(placesDetailsModel.getData().getName());
                underTittle.setText(placesDetailsModel.getData().getDescription());
                Location.setText(placesDetailsModel.getData().getLocation());
               // Utils.Place_id=placesDetailsModel.getData().
                 Categrey.setText(placesDetailsModel.getData().getCategory());
                RatingNumber.setText(placesDetailsModel.getData().getRate()+"");
                ratingBar.setRating(placesDetailsModel.getData().getRate());
                time.setText(placesDetailsModel.getData().getOpen_time());
                Glide.with(getActivity())
                        .asBitmap()
                        .load(placesDetailsModel.getData().getCover())
                        .into(Cover);
                Glide.with(getActivity())
                        .asBitmap()
                        .load(placesDetailsModel.getData().getLogo())
                        .into(Logo);
                if (placesDetailsModel.getData().getCover()==null)
                {
                    Glide.with(context)
                            .asBitmap()
                            .load(R.drawable.noimage)
                            .into(Cover);
                }
                if(placesDetailsModel.getData().getLogo()==null)
                {
                    Glide.with(context)
                            .asBitmap()
                            .load(R.drawable.noimage)
                            .into(Logo);
                }


                Log.e("rate", placesDetailsModel.getData().getRate() + "");
                Log.e("Title", Title + "");
                Utils.PlacesPhone = placesDetailsModel.getData().getPhone();
                PhonePlace = placesDetailsModel.getData().getPhone();
                facbook = placesDetailsModel.getData().getFacebook_link();
                instagram = placesDetailsModel.getData().getInstagram_link();
                twitter = placesDetailsModel.getData().getTwitter_link();
                website = placesDetailsModel.getData().getWebsite_link();
                youtupe = placesDetailsModel.getData().getYoutube_link();

                if (facbook==null)
                {
                    iv_ficon.setVisibility( View.GONE);
                }else
                    iv_ficon.setVisibility( View.VISIBLE);

                if (website==null)
                {
                    iv_wicon.setVisibility( View.GONE);
                }else
                    iv_wicon.setVisibility( View.VISIBLE);

                if (instagram==null)
                {
                    insta_icon.setVisibility( View.GONE);
                }
                if (twitter==null)
                {
                    twitter_icon.setVisibility( View.GONE);
                }
                if (youtupe==null)
                {
                    you_icon.setVisibility( View.GONE);
                }

                if(placesDetailsModel.getData().getLocation()==null)
                {
                    Location.setText("الموقع لم يحدد بعد");
                }
                if( placesDetailsModel.getData().getDescription()==null)
                {
                    underTittle.setText("لا يوجد وصف");
                }

//                OpenTIme = placesDetailsModel.getData().getOpen_time();
//                CloseTime = placesDetailsModel.getData().getClose_time();
                ////////////////////////  close or open time

                String[] open = placesDetailsModel.getData().getOpen_time().split(":"); //will break the string up into an array
                int hourO = Integer.parseInt(open[0]); //first element
                int minutesO = Integer.parseInt(open[1]); //second element
                OpenTIme = hourO +":"+ minutesO; //add up our values
                Log.i("OpenTIme", OpenTIme+"");


                String[] clos = placesDetailsModel.getData().getClose_time().split(":"); //will break the string up into an array
                int hourC = Integer.parseInt(clos[0]); //first element
                int minutesC = Integer.parseInt(clos[1]); //second element
                CloseTime = hourC +":"+ minutesC; //add up our values
                Log.i("CloseTime", CloseTime+"");

                 Calendar cal= Calendar.getInstance();
                 int pm=cal.get(Calendar.AM_PM);
                 int thisHour=cal.get(Calendar.HOUR);
                 int thisMinutes=cal.get(Calendar.MINUTE);

              if (pm==1) { thisHour+=12;
              }

                if (hourO >= hourC) { hourC += 24; }
               // if (hourO >= hourC) { hourC += 12; }
                Log.i("pm ", pm+"");
                Log.i("open hour", hourO+"");
                Log.i("close hour", hourC+"");
                Log.i("this hour", thisHour+"");
               // time_stute.setText("مغلق");
                  if(thisHour>=hourO && thisHour<=hourC)
                    {
                        time_stute.setText("مفتوح");
                        if(thisHour==hourO) {
                            if (thisMinutes >= minutesO) {
                                time_stute.setText("مفتوح");
                            }
                            else{
                                time_stute.setText("مغلق");
                            }
                        }
                    }
                 /* else
                      time_stute.setText("مغلق");*/


               /* if (hourO >= hourC) { hourC += 24; }
                if (thisHour >= hourO||thisHour <= hourC ){
                    if (thisHour == hourO|| thisMinutes < minutesO) {
                        time_stute.setText("مغلق");
                    }
                    if (thisHour == hourC|| thisMinutes >= minutesC) {
                        time_stute.setText("مغلق");
                    }
                    time_stute.setText("مفتوح");
                }
                time_stute.setText("مغلق");


                */

              /*  if (OpenTIme.compareTo(CloseTime) > 0)
                {
                    Log.i("app", "Date1 is after Date2");
                    time_stute.setText("مفتوح");
                    Log.e("date222", CloseTime + "");

                }
                else if (OpenTIme.compareTo(CloseTime) < 0)
                {    Log.i("app", "Date1 is before Date2");
                    time_stute.setText("مغلق");
                }
                else if (OpenTIme.compareTo(CloseTime) == 0)
                {    Log.i("app", "Date1 is equal to Date2");}*/


               // time.setText( placesDetailsModel.getData().getClose_time()+  "  :  "+placesDetailsModel.getData().getOpen_time()   );
                time.setText( CloseTime+  "  :  "+OpenTIme );
                if (placesDetailsModel.getData().getCan_animal() == 1) {
                    animal.setVisibility(View.VISIBLE);
                } else
                    animal.setVisibility(View.GONE);

                if (placesDetailsModel.getData().getCan_delivery() == 1) {
                    delivery.setVisibility(View.VISIBLE);
                } else
                    delivery.setVisibility(View.GONE);

                if (placesDetailsModel.getData().getCan_music() == 1) {
                    can_music.setVisibility(View.VISIBLE);
                } else
                    can_music.setVisibility(View.GONE);


                if (placesDetailsModel.getData().getType_id()==2) {
                    type.setBackgroundResource(R.drawable.rmini2);

                } else  {
                    type.setBackgroundResource(R.drawable.rmini3);
                }

                Log.e("PhonePlace", PhonePlace + "");
                MarketMenuAdapter adapter3 = new MarketMenuAdapter(restaurantFragment, getContext(), placesDetailsModel.getData());
                LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                Menu_images.setLayoutManager(layoutManager3);
                Menu_images.setHasFixedSize(true);
                Menu_images.setAdapter(adapter3);


                EndProgress();
            }
        });
        mViewModel.Rate.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                rate_popup.setVisibility(View.GONE);
                EndProgress();
            }
        });
    }
    private void setup() {
        SharedPreferences shared = this.getActivity().getSharedPreferences("info",MODE_PRIVATE);
        Name_olde=shared.getString("UserName", null);
        Phone_Old=shared.getString("Phone", null);
        Phonenumber.setText(Phone_Old);
        Name.setText(  Name_olde);

    /*    MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // Toast.makeText(getContext(), " successful ", Toast.LENGTH_SHORT).show();
            }
        });*/
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Log.e("date1", OpenTIme + "");
        Log.e("date2", CloseTime + "");
   /*       if (facbook==null)
          {
              iv_ficon.setVisibility( View.GONE);
          }else
              iv_ficon.setVisibility( View.VISIBLE);

             if (website==null)
          {
              iv_wicon.setVisibility( View.GONE);
          }else
                 iv_wicon.setVisibility( View.VISIBLE);

        if (instagram==null)
          {
              insta_icon.setVisibility( View.GONE);
          }
          if (twitter==null)
          {
              twitter_icon.setVisibility( View.GONE);
          }
          if (youtupe==null)
          {
              you_icon.setVisibility( View.GONE);
          }*/
        // compareDates();
        /*
         func isOpened() -> Bool {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "hh:mm:ss"

        guard let openDate = dateFormatter.date(from: open ?? ""),
         let closeDate = dateFormatter.date(from: close ?? "") else { return false }

        let thisHour = Calendar.current.component(.hour, from: Date())
        let openHour = Calendar.current.component(.hour, from: openDate)
        var closeHour = Calendar.current.component(.hour, from: closeDate)

        let thisMin = Calendar.current.component(.minute, from: Date())
        let openMin = Calendar.current.component(.minute, from: openDate)
        let closeMin = Calendar.current.component(.minute, from: closeDate)

        if openHour >= closeHour {
            closeHour += 24
        }

        if thisHour >= openHour, thisHour <= closeHour {
            if thisHour == openHour, thisMin < openMin {
                return false
            }
            if thisHour == closeHour, thisMin >= closeMin {
                return false
            }
            return true
        }
        return false
    }*/
    }
    private void Actions() {
        Add_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate_popup.setVisibility(View.VISIBLE);
            }
        });
        Sendrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().isEmpty()||Phonenumber.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "من فضلك ادخل الاسم و رقم الهاتف", Toast.LENGTH_SHORT).show();
                }else {
                    StartProgress();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("UserName",Name.getText().toString());
                    editor.putString("Phone",Phonenumber.getText().toString());
                    editor.commit();
                    mViewModel.SendRate(Name.getText().toString(), Phonenumber.getText().toString(), rate.getRating(), Note.getText().toString());
                }
            }
        });
        Calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PhonePlace.trim().length() > 0) {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        String dial = "tel:" + PhonePlace;
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                } else {
                    Toast.makeText(getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_ficon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("facbook", facbook + "");

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(facbook));
                startActivity(i);
                //   getFBIntent(context, facbook);
            }
        });
        you_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(youtupe));
                startActivity(i);
            }
        });
        twitter_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(twitter));
                startActivity(i);
            }
        });
        insta_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
                startActivity(i);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menu_images.setVisibility(View.VISIBLE);
                layout_menue.setVisibility(View.VISIBLE);
                StartProgress();
                mViewModel.GetPlaceDetail();


            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_menue.setVisibility(View.GONE);
                Log.e("close", "close");

            }
        });
        closeR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate_popup.setVisibility(View.GONE);
                Log.e("close", "close");

            }
        });
        closeRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToFragment(new HomeFragment());

            }
        });

    }
    private void Definations(View root) {
        Progress = root.findViewById(R.id.Progress);
        Layout = root.findViewById(R.id.Layout);
        Phonenumber = root.findViewById(R.id.iv_phonenumber);
        Cover = root.findViewById(R.id.iv_MainPhoto);
        Logo = root.findViewById(R.id.iv_MainIcon);
        rate_popup = root.findViewById(R.id.rate_popup);
        Name = root.findViewById(R.id.iv_name);
        Add_rate = root.findViewById(R.id.add_rate);
        Sendrate = root.findViewById(R.id.tv_sendrate);
        Note = root.findViewById(R.id.note);
        rate = root.findViewById(R.id.rate);
        Title = root.findViewById(R.id.tv_sideTittle);
        underTittle = root.findViewById(R.id.underTittle);
        Location = root.findViewById(R.id.tv_location);
        RatingNumber = root.findViewById(R.id.tv_RatingNumber);
        ratingBar = root.findViewById(R.id.ratingBar);
        time = root.findViewById(R.id.tv_time);
        Calling = root.findViewById(R.id.iv_Callicon);
        iv_ficon = root.findViewById(R.id.iv_ficon);
        iv_wicon = root.findViewById(R.id.iv_wicon);
        you_icon = root.findViewById(R.id.iv_yicon);
        twitter_icon = root.findViewById(R.id.twitter_icon);
        insta_icon = root.findViewById(R.id.insta_icon);
        animal = root.findViewById(R.id.iv_mini4);
        type = root.findViewById(R.id.type);
        time_stute = root.findViewById(R.id.time_stute);
        delivery = root.findViewById(R.id.delivery);
        menu = root.findViewById(R.id.menu);
        Menu_images = root.findViewById(R.id.select_menu_images);
        close = root.findViewById(R.id.close);
        layout_menue = root.findViewById(R.id.layout_menue);
        PlaceName = root.findViewById(R.id.iv_upTitleBorder);
        closeR=root.findViewById(R.id.closeR);
        closeRest=root.findViewById(R.id.closeRest);
        can_music=root.findViewById(R.id.can_music);
        Categrey=root.findViewById(R.id.type_name);
        mAdView = root.findViewById(R.id.adView);


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