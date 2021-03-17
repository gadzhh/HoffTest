package com.example.hofftest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("new")
    @Expose
    private int newPrice;

    @SerializedName("old")
    @Expose
    private int oldPrice;

    public int getNewPrice() {
        return newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }
}
