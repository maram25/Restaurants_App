package com.saudi_restaurants_and_cafes.android.Ui.Restaurant;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saudi_restaurants_and_cafes.android.Calls.APIClient;
import com.saudi_restaurants_and_cafes.android.Calls.APIInterface;
import com.saudi_restaurants_and_cafes.android.Models.HelpModel;
import com.saudi_restaurants_and_cafes.android.Models.PlacesDetailsModel;
import com.saudi_restaurants_and_cafes.android.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantViewModel extends ViewModel {


    MutableLiveData<Boolean> Rate = new MutableLiveData<Boolean>();
    MutableLiveData<PlacesDetailsModel> PlaceDetails = new MutableLiveData<>();
    MutableLiveData<String> NoInternet = new MutableLiveData<>();


    MutableLiveData<Boolean> UnAuth = new MutableLiveData<>();

    APIInterface apiInterface;

    public void GetPlaceDetail() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<PlacesDetailsModel> call = apiInterface.GetPlaceDetails(Utils.Place_id);
        call.enqueue(new Callback<PlacesDetailsModel>() {
            @Override
            public void onResponse(Call<PlacesDetailsModel> call, Response<PlacesDetailsModel> response) {
                if (response.isSuccessful()) {
                    PlacesDetailsModel resource = response.body();
                    if (response.isSuccessful())
                        PlaceDetails.setValue(resource);

                } else if (response.code() == 401)
                    UnAuth.setValue(true);

            }

            @Override
            public void onFailure(Call<PlacesDetailsModel> call, Throwable t) {

            }
        });
    }

    public void SendRate(String name, String phone, Float rate, String comment) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<HelpModel> call = apiInterface.MakeReview(name, phone,Utils.Place_id, rate, comment);
        call.enqueue(new Callback<HelpModel>() {
            @Override
            public void onResponse(Call<HelpModel> call, Response<HelpModel> response) {
                if (response.isSuccessful())
                    Rate.setValue(true);
                else if (response.code() == 401)
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<HelpModel> call, Throwable t) {
                NoInternet.setValue(t.getMessage());
                Log.e("Fail", t.getMessage());
            }
        });

    }
}