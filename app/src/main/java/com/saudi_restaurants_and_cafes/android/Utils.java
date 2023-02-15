package com.saudi_restaurants_and_cafes.android;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;


public class
Utils {







  //  SharedPreferences sharedPreferences= getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);


    public static int SelectedCityId=6;
    public static String SelectedCity="الرياض";



    public static String sort_by="";
    public static String Lang="en";
    public static String search="";
    public static String PlacesPhone="";
    public static int PlacesTypes=1,Can_children=0,Categories=0,Can_music=0,Can_famil=0,Can_animal=0,Place_id=0,Featured=1;

    public static ArrayList<Integer>place_characteristics=new ArrayList<>();
    public static ArrayList<Integer> Cooktypes=new ArrayList<>();
    public static ArrayList<Integer>rate=new ArrayList<>();
   // public static int rate;

    public static String PhoneNumber = "";
    public static String Name = "";
    public static Boolean Fourth =false;




}
