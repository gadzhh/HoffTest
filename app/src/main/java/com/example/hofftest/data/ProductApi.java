package com.example.hofftest.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("get_products_new")
    Call<ProductsResponse> getData(
            @Query("category_id") int category_id,
            @Query("sort_by") String sortBy,
            @Query("sort_type") String sortType,
            @Query("discount") String discount,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("device_id") String deviceId,
            @Query("app_version") String appVersion,
            @Query("isAndroid") Boolean isAndroid,
            @Query("location") int location
    );
}
