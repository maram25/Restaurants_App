package com.saudi_restaurants_and_cafes.android.Ui.Filter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.saudi_restaurants_and_cafes.android.Calls.APIClient;
import com.saudi_restaurants_and_cafes.android.Calls.APIInterface;
import com.saudi_restaurants_and_cafes.android.Models.AllFillterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterViewModel extends ViewModel {

    MutableLiveData<String> NoInternet = new MutableLiveData<>();
    MutableLiveData<Boolean> UnAuth = new MutableLiveData<>();
    MutableLiveData<List<AllFillterModel.Cooking_types>> filterCookType = new MutableLiveData<>();
    MutableLiveData<List<AllFillterModel.Place_characteristics>> filterPlaceChar = new MutableLiveData<>();
    MutableLiveData<AllFillterModel> filter = new MutableLiveData<>();

    APIInterface apiInterface;


    public void GetFilterCookingTypes() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllFillterModel> call = apiInterface.GetِFilter() ;
        call.enqueue(new Callback<AllFillterModel>() {
            @Override
            public void onResponse(Call<AllFillterModel> call, Response<AllFillterModel> response) {
                AllFillterModel resource=response.body();
                if (response.isSuccessful()) {
                    filterCookType.setValue(resource.getCooking_types());

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AllFillterModel> call, Throwable t) {

            }
        });
    }


    public void GetFilterplacechar() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllFillterModel> call = apiInterface.GetِFilter() ;
        call.enqueue(new Callback<AllFillterModel>() {
            @Override
            public void onResponse(Call<AllFillterModel> call, Response<AllFillterModel> response) {
                AllFillterModel resource=response.body();
                if (response.isSuccessful()) {
                    filterPlaceChar.setValue(resource.getPlace_characteristics());

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AllFillterModel> call, Throwable t) {

            }
        });
    }

 public void GetFilter() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<AllFillterModel> call = apiInterface.GetِFilter() ;
        call.enqueue(new Callback<AllFillterModel>() {
            @Override
            public void onResponse(Call<AllFillterModel> call, Response<AllFillterModel> response) {
                AllFillterModel resource=response.body();
                if (response.isSuccessful()) {
                    filter.setValue(resource);

                }else  if ( response.code() == 401 )
                    UnAuth.setValue(true);
            }

            @Override
            public void onFailure(Call<AllFillterModel> call, Throwable t) {

            }
        });
    }





    }









