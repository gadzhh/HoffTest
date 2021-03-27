package com.example.hofftest.screens.products;

import com.example.hofftest.data.models.Products;
import com.example.hofftest.data.models.ProductsResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ProductListView {

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showData(ProductsResponse model);

    void applyNewProducts(ArrayList<Products> products);
}
