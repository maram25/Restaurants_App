package com.saudi_restaurants_and_cafes.android.Ui.ArrangeBy;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saudi_restaurants_and_cafes.android.Calls.APIClient;
import com.saudi_restaurants_and_cafes.android.Calls.APIInterface;
import com.saudi_restaurants_and_cafes.android.Models.AllPlacesModel;
import com.saudi_restaurants_and_cafes.android.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArrangeByViewModel extends ViewModel {
    MutableLiveData<String> NoInternet = new MutableLiveData<>();
    MutableLiveData<Boolean> UnAuth = new MutableLiveData<>();
    MutableLiveData<List<AllPlacesModel.Place>> place = new MutableLiveData<>();
    APIInterface apiInterface;


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
                    place.setValue(resource.getData());

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