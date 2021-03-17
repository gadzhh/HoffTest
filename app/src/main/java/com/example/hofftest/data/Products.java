package com.example.hofftest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("statusText")
    @Expose
    private String status;

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("numberOfReviews")
    @Expose
    private int numberOfReviews;

    @SerializedName("prices")
    @Expose
    private Price prices;

    private boolean isFavorite;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Price getPrices() {
        return prices;
    }
}

