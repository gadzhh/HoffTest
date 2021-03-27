package com.example.hofftest.screens.products;

import com.example.hofftest.App;
import com.example.hofftest.data.models.Products;
import com.example.hofftest.data.models.ProductsResponse;
import com.example.hofftest.data.networks.RequestListener;

import java.util.ArrayList;

public class ProductListPresenter {

    private int PAGE_LIMIT = 40;
    private ProductListView productListView;
    private int categoryId = 320;
    private String sortBy = "popular";
    private String sortType = "asc";
    private String discount = "N";
    private int offset = 0;

    private boolean isLoading = false;
    private boolean allPagesLoaded = false;

    private ArrayList<Products> mergedProducts = new ArrayList<>();

    public ProductListPresenter(ProductListView view) {
        productListView = view;
    }

    public void viewIsReady() {

        fetchProducts(
                categoryId,
                sortBy,
                sortType,
                discount
        );
    }

    public void fetchProducts(
            int categoryId,
            String sortBy,
            String sortType,
            String discount
    ) {

        if (isLoading) return;

        isLoading = true;
        offset = 0;
        allPagesLoaded = false;

        productListView.showProgress();
        this.categoryId = categoryId;
        this.sortBy = sortBy;
        this.sortType = sortType;

        App.getDataManager().getProductItems(categoryId, sortBy, sortType, discount, PAGE_LIMIT, offset, "3a7612bcc84813b5", "1.8.32", true, 19, new RequestListener() {
            @Override
            public void onSuccess(ProductsResponse model) {

                mergedProducts.clear();
                mergedProducts.addAll(model.getProducts());

                productListView.hideProgress();
                productListView.showData(model);

                offset += model.getProducts().size();

                if (model.getProducts().size() < PAGE_LIMIT)
                    allPagesLoaded = true;

                isLoading = false;
            }

            @Override
            public void onError(String error) {
                productListView.hideProgress();
                productListView.showError(error);
                isLoading = false;
            }
        });
    }

    public void fetchNextPage() {

        if (isLoading || allPagesLoaded) return;

        isLoading = true;

        App.getDataManager().getProductItems(categoryId, sortBy, sortType, discount, PAGE_LIMIT, offset, "3a7612bcc84813b5", "1.8.32", true, 19, new RequestListener() {
            @Override
            public void onSuccess(ProductsResponse model) {

                mergedProducts.addAll(model.getProducts());

                productListView.hideProgress();
                productListView.applyNewProducts(mergedProducts);

                offset += model.getProducts().size();

                if (model.getProducts().size() < PAGE_LIMIT)
                    allPagesLoaded = true;

                isLoading = false;
            }

            @Override
            public void onError(String error) {
                productListView.hideProgress();
                productListView.showError(error);
                isLoading = false;
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
