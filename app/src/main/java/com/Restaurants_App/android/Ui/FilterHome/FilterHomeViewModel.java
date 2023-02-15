package com.Restaurants_App.android.Ui.FilterHome;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Restaurants_App.android.Calls.APIClient;
import com.Restaurants_App.android.Calls.APIInterface;
import com.Restaurants_App.android.Models.AllFillterModel;
import com.Restaurants_App.android.Models.AllPlacesModel;
import com.Restaurants_App.android.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FilterHomeViewModel extends ViewModel {
    MutableLiveData<String> NoInternet = new MutableLiveData<>();
    MutableLiveData<Boolean> UnAuth = new MutableLiveData<>();
    MutableLiveData<List<AllPlacesModel.Place>>AllPlaces = new MutableLiveData<>();
    MutableLiveData<List<AllFillterModel.Categories>> FilterCato = new MutableLiveData<>();
    MutableLiveData<List<AllFillterModel.Categories>>filtetCategry = new MutableLiveData<>();
    APIInterface apiInterface;


    public void GetFilter() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllFillterModel> call = apiInterface.GetِFilter() ;
        call.enqueue(new Callback<AllFillterModel>() {
            @Override
            public void onResponse(Call<AllFillterModel> call, Response<AllFillterModel> response) {
                AllFillterModel resource=response.body();
                if (response.isSuccessful()) {
                    filtetCategry.setValue(resource.getCategories());

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AllFillterModel> call, Throwable t) {

            }
        });
    }

/*
    public void AllFilter(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllFillterModel> call = apiInterface.GetِFilter();
        call.enqueue(new Callback<AllFillterModel>() {
            @Override
            public void onResponse(Call<AllFillterModel> call, Response<AllFillterModel> response) {

                AllFillterModel resource=response.body();
                if (response.isSuccessful()) {
                    FilterCato.setValue(AllFillterModel.Categories);

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);

            }

            @Override
            public void onFailure(Call<AllFillterModel> call, Throwable t) {

            }
        });



    }*/

    public void AllPlace() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllPlacesModel> call = apiInterface.GetAllPlaces(Utils.SelectedCityId,Utils.PlacesTypes,Utils.Categories,
               Utils.rate, Utils.Can_children, Utils.Can_animal,Utils.Can_music,Utils.Can_famil,Utils.Featured,Utils.Cooktypes,Utils.place_characteristics,
                Utils.sort_by,Utils.search);

        call.enqueue(new Callback<AllPlacesModel>() {
            @Override
            public void onResponse(Call<AllPlacesModel> call, Response<AllPlacesModel> response) {
                AllPlacesModel resource=response.body();
                if (response.isSuccessful()) {
                    AllPlaces.setValue(resource.getData());

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AllPlacesModel> call, Throwable t) {
                NoInternet.setValue(t.getMessage());

            }
        });
    }
}