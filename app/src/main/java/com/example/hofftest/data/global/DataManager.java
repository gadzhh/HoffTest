package com.example.hofftest.data.global;

import com.example.hofftest.App;
import com.example.hofftest.data.networks.RequestListener;
import com.example.hofftest.data.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    public void getProductItems(int category_id, String sortBy, String sortType, String discount, int limit, int offset, String deviceId, String appVersion, Boolean isAndroid, int location, RequestListener requestListener) {

        App.getJSONApi().getData(category_id, sortBy, sortType, discount, limit, offset, deviceId, appVersion, isAndroid, location)
                .enqueue(new Callback<ProductsResponse>() {
                    @Override
                    public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                        if (response.body() != null) {
                            requestListener.onSuccess(response.body());
                        } else {
                            requestListener.onError("Data is Empty");
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductsResponse> call, Throwable throwable) {
                        requestListener.onError("" + throwable.getLocalizedMessage());
                    }
                });
    }
}
