package com.example.hofftest.data.models;

import com.google.gson.annotations.SerializedName;

public class RelatedCategory {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
