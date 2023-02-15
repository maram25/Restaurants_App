package com.Restaurants_App.android.Ui.AboutUs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Restaurants_App.android.Calls.APIClient;
import com.Restaurants_App.android.Calls.APIInterface;
import com.Restaurants_App.android.Models.AboutUs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsViewModel extends ViewModel {
    MutableLiveData<String> NoInternet = new MutableLiveData<>();
    MutableLiveData<Boolean> UnAuth = new MutableLiveData<>();
    MutableLiveData<AboutUs> Aboutus = new MutableLiveData<>();
    APIInterface apiInterface;

    public void GetAboutAs(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AboutUs> call = apiInterface.GetAboutUs();
        call.enqueue(new Callback<AboutUs>() {
            @Override
            public void onResponse(Call<AboutUs> call, Response<AboutUs> response) {
                AboutUs resource=response.body();
                if (response.isSuccessful()) {
                    Aboutus.setValue(resource);

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AboutUs> call, Throwable t) {
                NoInternet.setValue(t.getMessage());
            }
        });
}}