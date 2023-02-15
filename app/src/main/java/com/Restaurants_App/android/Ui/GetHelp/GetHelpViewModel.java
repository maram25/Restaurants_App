package com.Restaurants_App.android.Ui.GetHelp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Restaurants_App.android.Calls.APIClient;
import com.Restaurants_App.android.Calls.APIInterface;
import com.Restaurants_App.android.Models.HelpModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetHelpViewModel extends ViewModel {


    MutableLiveData<Boolean> Help = new MutableLiveData<Boolean>();
    MutableLiveData<String> NoInternet=new MutableLiveData<>();


    MutableLiveData<Boolean>UnAuth = new MutableLiveData<>();

    APIInterface apiInterface;
    public void SendHelpForm ( String name,String phone ,String comment){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<HelpModel> call = apiInterface.HelpForm( name, phone,comment);
         call.enqueue(new Callback<HelpModel>() {
             @Override
             public void onResponse(Call<HelpModel> call, Response<HelpModel> response) {
                 if (response.isSuccessful())
                     Help.setValue(true);
                 else  if ( response.code() == 401 )
                     UnAuth.setValue(true);
             }

             @Override
             public void onFailure(Call<HelpModel> call, Throwable t) {
                 NoInternet.setValue(t.getMessage());
                 Log.e("Fail",t.getMessage());
             }
         });
    }

}