package com.saudi_restaurants_and_cafes.android.Ui.Home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saudi_restaurants_and_cafes.android.Calls.APIClient;
import com.saudi_restaurants_and_cafes.android.Calls.APIInterface;
import com.saudi_restaurants_and_cafes.android.Models.CitiesModel;
import com.saudi_restaurants_and_cafes.android.Models.PlaceHomeModel;
import com.saudi_restaurants_and_cafes.android.Models.TypesModel;
import com.saudi_restaurants_and_cafes.android.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {


    MutableLiveData<List<CitiesModel.Cities>> Cities = new MutableLiveData<List<CitiesModel.Cities>>();
    MutableLiveData<List<TypesModel.Types>> Types = new MutableLiveData<List<TypesModel.Types>>();
    MutableLiveData<PlaceHomeModel> PlaceHome = new MutableLiveData<PlaceHomeModel>();
  //  MutableLiveData<List<PlaceHomeModel.First>> PlaceHome = new MutableLiveData<List<PlaceHomeModel.First>>();
    MutableLiveData<String> NoInternet=new MutableLiveData<>();
    MutableLiveData<Boolean>UnAuth = new MutableLiveData<>();
    MutableLiveData<String> ErrorMessage = new MutableLiveData<>();

    List<String> Districts = new ArrayList<>();
    List<Integer> DistrictsIds = new ArrayList<>();
    APIInterface apiInterface;
    public void GetCities( ) {
        Districts = new ArrayList<>();
        DistrictsIds = new ArrayList<>();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CitiesModel> call = apiInterface.GetCities();
        call.enqueue(new Callback<CitiesModel>() {
            @Override
            public void onResponse(Call<CitiesModel> call, Response<CitiesModel> response) {
                CitiesModel resource=response.body();
                if (response.isSuccessful()) {
                    Cities.setValue(resource.getData());
                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }
            @Override
            public void onFailure(Call<CitiesModel> call, Throwable t) {
                NoInternet.setValue(t.getMessage());
                Log.d("error", "on failere govermant");
            }
        });
    }
    public void GetType( ) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<TypesModel> call = apiInterface.GetTypes();
        call.enqueue(new Callback<TypesModel>() {
            @Override
            public void onResponse(Call<TypesModel> call, Response<TypesModel> response) {
                TypesModel resource=response.body();
                if (response.isSuccessful()) {
                    Types.setValue(resource.getData());
                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<TypesModel> call, Throwable t) {
                NoInternet.setValue(t.getMessage());
                Log.d("error", "on failere govermant");
            }
        });
    }
    public void GetPlaceHome() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlaceHomeModel> call = apiInterface.GetPlaceHome(Utils.PlacesTypes,Utils.SelectedCityId);
        call.enqueue(new Callback<PlaceHomeModel>() {
            @Override
            public void onResponse(Call<PlaceHomeModel> call, Response<PlaceHomeModel> response) {
                PlaceHomeModel resource=response.body();
                if (response.isSuccessful()) {
                    Log.e("PlacesTypes", Utils.PlacesTypes + "");
                    Log.e("data", String.valueOf(resource.first.size()));

                    PlaceHome.setValue(resource);
                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<PlaceHomeModel> call, Throwable t) {
                NoInternet.setValue(t.getMessage());
                Log.d("error__",t.getMessage());
            }
        });
    }

}