package com.example.hofftest.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hofftest.R;
import com.example.hofftest.screens.products.ProductFragment;

public class HomeFragment extends Fragment {

    Button goToCatalogButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }


    private void initView(View view) {

        goToCatalogButton = view.findViewById(R.id.btn_go_to_catalog);
        goToCatalogButton.setOnClickListener(view1 -> {

            ProductFragment productFragment = new ProductFragment();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_for_fragments, productFragment).addToBackStack(null);
            fragmentTransaction.commit();
        });
    }
}
