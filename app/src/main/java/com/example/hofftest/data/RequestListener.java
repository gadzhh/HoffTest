package com.example.hofftest.data;

import java.util.ArrayList;

public interface RequestListener {

    void onSuccess(ProductsResponse model);

    void onError(String error);
}
