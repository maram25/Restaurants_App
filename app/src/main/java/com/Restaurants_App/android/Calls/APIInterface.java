package com.Restaurants_App.android.Calls;


import com.Restaurants_App.android.Models.AboutUs;
import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.Models.AllPlacesModel;
import com.Restaurants_App.android.Models.CitiesModel;
import com.Restaurants_App.android.Models.HelpModel;
import com.Restaurants_App.android.Models.PlaceHomeModel;
import com.Restaurants_App.android.Models.PlacesDetailsModel;
import com.Restaurants_App.android.Models.TypesModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface
APIInterface {

  /*  //login
    @FormUrlEncoded
    @POST("api/v1/driver/login")
    @Headers({"Accept: application/json"})
    Call<LoginModel> Login(
            @Field("phone") String phone_number,
            @Field("password") String password
            , @Field("device_type") String DeviceType,
             @Field("fcm_token") String FcmToken

    );

    //Getdrivercategories
    @GET("api/v1/drivercategories")
    @Headers({"Accept: application/json"})
        Call<CategoriesModel> GetCategories(
            @Header("authorization") String Token,
            @Query("locale") String Locale);*/

    //Getcities
    @GET("api/v1/cities")
    @Headers({"Accept: application/json"})
    Call<CitiesModel> GetCities();

    //Gettypes
    @GET("api/v1/types")
    @Headers({"Accept: application/json"})
    Call<TypesModel> GetTypes();

///helpforms
    @FormUrlEncoded
    @POST("api/v1/helpforms")
    @Headers({"Accept: application/json"})
    Call<HelpModel> HelpForm(
            @Field("name") String name,
            @Field("phone") String phone_number,
            @Field("comment") String Comment
    );

    /// REVIEW
    @FormUrlEncoded
    @POST("api/v1/reviews")
    @Headers({"Accept: application/json"})
    Call<HelpModel> MakeReview(
            @Field("name") String name,
            @Field("phone") String phone_number,
            @Field("place_id") int place_id,
            @Field("rate") float rate,
            @Field("comment") String Comment
    );


    //Getplaces
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("api/v1/places")
    Call<AllPlacesModel>GetAllPlaces(
            @Field("city_id") int city_id,
            @Field("type_id") int type_id,
            @Field("category_id") int category_id,
            @Field("rate[]") ArrayList<Integer>  rate,
            @Field("can_children") int can_children,
            @Field("can_animal") int can_animal,
            @Field("can_music") int can_music,
            @Field("can_family") int can_family,
            @Field("featured") int featured,
            @Field("cooktypes") ArrayList<Integer> cooktypes,
            @Field("place_characteristics") ArrayList<Integer> place_characteristics,
            @Field("sort_by") String sort_by,
            @Field("search") String search
  );


    //GetplacesHome
    @Headers({"Accept: application/json"})
    @GET("api/v1/places/home")
        Call<PlaceHomeModel> GetPlaceHome(@Query("type_id") int type_id,@Query("city_id") int city_id);


    //Getplacedetaails
    @Headers({"Accept:application/json"})
    @GET("api/v1/places/show")
    Call<PlacesDetailsModel> GetPlaceDetails(
            @Query("place_id") int place_id

    );


    //filter
    @GET("api/v1/filter/alloptions")
    @Headers({"Accept: application/json"})
    Call<AllFillterModel> GetِFilter(
    );


    //About us
    @Headers({"Accept: application/json"})
    @GET("api/v1/staticpages")
    Call<AboutUs> GetAboutUs(
    );




}
