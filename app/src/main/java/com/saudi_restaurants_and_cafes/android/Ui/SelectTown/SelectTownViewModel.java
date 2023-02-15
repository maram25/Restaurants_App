package com.saudi_restaurants_and_cafes.android.Ui.SelectTown;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saudi_restaurants_and_cafes.android.Calls.APIClient;
import com.saudi_restaurants_and_cafes.android.Calls.APIInterface;
import com.saudi_restaurants_and_cafes.android.Models.CitiesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTownViewModel extends ViewModel {

    MutableLiveData<List<CitiesModel.Cities>> Cities = new MutableLiveData<List<CitiesModel.Cities>>();
    MutableLiveData<String> NoInternet=new MutableLiveData<>();


    MutableLiveData<Boolean>UnAuth = new MutableLiveData<>();

    APIInterface apiInterface;

    public void GetCities( ) {


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
}