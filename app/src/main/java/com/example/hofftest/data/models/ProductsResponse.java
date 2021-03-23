package com.example.hofftest.data.models;

import com.example.hofftest.data.models.Products;
import com.example.hofftest.data.models.RelatedCategory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {

    @SerializedName("items")
    private List<Products> products;

    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("relatedCategories")
    private List<RelatedCategory> relatedCategories;

    public String getCategoryName() {
        return categoryName;
    }

    public List<RelatedCategory> getRelatedCategories() {
        return relatedCategories;
    }

    public List<Products> getProducts() {
        return products;
    }
}
