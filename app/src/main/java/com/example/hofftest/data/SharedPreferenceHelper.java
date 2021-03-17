package com.example.hofftest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferenceHelper {

    private static final String PREFS_APP = "prefs_app";
    private static final String PREFS_CART = "prefs_cart";

    private static SharedPreferences prefs;
    private Context context;

    public SharedPreferenceHelper(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE);
    }

    public boolean idInBasket(int productId) {

        ArrayList<Integer> cart;
        Gson gson = new Gson();

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);

        if (cart != null && !cart.isEmpty()) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).equals(productId)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addToFavorite(int productId) {

        ArrayList<Integer> cart;
        Gson gson = new Gson();

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);

        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(productId);

        prefs.edit().putString(PREFS_CART, gson.toJson(cart)).apply();
    }

    public void removeFromFavorite(int productId) {

        ArrayList<Integer> cart;
        Gson gson = new Gson();

        String json = prefs.getString(PREFS_CART, "");
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        cart = gson.fromJson(json, type);

        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i) == productId) {
                    cart.remove(i);
                }
            }
        }

        prefs.edit().putString(PREFS_CART, gson.toJson(cart)).apply();
    }
}
