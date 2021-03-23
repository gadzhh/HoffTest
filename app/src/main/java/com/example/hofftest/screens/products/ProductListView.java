package com.example.hofftest.screens.products;

import com.example.hofftest.data.models.ProductsResponse;

public interface ProductListView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showData(ProductsResponse model);
}
