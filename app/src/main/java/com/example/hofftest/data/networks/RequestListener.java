package com.example.hofftest.data.networks;

import com.example.hofftest.data.models.ProductsResponse;

public interface RequestListener {

    void onSuccess(ProductsResponse model);

    void onError(String error);
}
