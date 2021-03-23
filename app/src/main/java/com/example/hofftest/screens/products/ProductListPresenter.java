package com.example.hofftest.screens.products;

import com.example.hofftest.App;
import com.example.hofftest.data.models.ProductsResponse;
import com.example.hofftest.data.networks.RequestListener;

public class ProductListPresenter {

    private ProductListView productListView;
    private int categoryId = 320;
    private String sortBy = "popular";
    private String sortType = "asc";
    private String discount = "N";
    private int offset = 0;

    public ProductListPresenter(ProductListView view) {
        productListView = view;
    }

    public void viewIsReady() {

        fetchProducts(
                categoryId,
                sortBy,
                sortType,
                discount,
                offset
        );
    }

    public void fetchProducts(
            int categoryId,
            String sortBy,
            String sortType,
            String discount,
            int offset
    ) {

        productListView.showProgress();
        this.categoryId = categoryId;
        this.sortBy = sortBy;
        this.sortType = sortType;
        this.offset = offset;

        App.getDataManager().getProductItems(categoryId, sortBy, sortType, discount, 20, offset, "3a7612bcc84813b5", "1.8.32", true, 19, new RequestListener() {
            @Override
            public void onSuccess(ProductsResponse model) {
                productListView.hideProgress();
                productListView.showData(model);
            }

            @Override
            public void onError(String error) {
                productListView.hideProgress();
                productListView.showError(error);
            }
        });
    }
}

/*
  сначала дешевые - sortBy = popular, sortType = desc
  сначала дорогие - sortBy = popular, sortType = asc
  по скидкам - sortBy = discount, sortType = asc
  по популярности - sortBy = popular, sortType = desc
 */
