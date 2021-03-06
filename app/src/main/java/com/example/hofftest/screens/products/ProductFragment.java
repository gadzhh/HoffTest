package com.example.hofftest.screens.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofftest.R;
import com.example.hofftest.data.SharedPreferenceHelper;
import com.example.hofftest.data.models.Products;
import com.example.hofftest.data.models.ProductsResponse;
import com.example.hofftest.screens.home.HomeFragment;

import java.util.ArrayList;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class ProductFragment extends Fragment implements ProductListView, OnButtonListener, AdapterView.OnItemSelectedListener {

    private ProductItemsAdapter productItemsAdapter;
    private UpperItemsAdapter upperItemsAdapter;
    private SharedPreferenceHelper preferenceHelper;
    private ProductListPresenter presenter;
    private FrameLayout progress;
    private AppCompatSpinner spinnerBtn;
    private GridLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initPrefs();
        initSpinner();
        initPresenter();
    }

    private void initViews(View view) {

        RecyclerView mainProductList, upperProductList;
        Toolbar toolbar;

        layoutManager = new GridLayoutManager(getContext(), 2);

        progress = view.findViewById(R.id.progress);
        spinnerBtn = view.findViewById(R.id.spinner);
        toolbar = view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(view1 -> {
            HomeFragment homeFragment = new HomeFragment();

            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction()
                    .replace(R.id.container_for_fragments, homeFragment)
                    .addToBackStack(null);
            fragmentTransaction.commit();
        });

        mainProductList = view.findViewById(R.id.rv_main);
        upperProductList = view.findViewById(R.id.rv_upper);

        mainProductList.setLayoutManager(layoutManager);
        productItemsAdapter = new ProductItemsAdapter(this);
        mainProductList.setHasFixedSize(true);
        mainProductList.setAdapter(productItemsAdapter);

        upperItemsAdapter = new UpperItemsAdapter();
        upperProductList.setHasFixedSize(true);
        upperProductList.setAdapter(upperItemsAdapter);

        mainProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = productItemsAdapter.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (totalItemCount - lastVisibleItemPosition < 10) {
                    presenter.fetchNextPage();
                }
            }
        });
    }

    private void initPresenter() {
        presenter = new ProductListPresenter(this);
        presenter.viewIsReady();
    }

    private void initPrefs() {
        if (Objects.requireNonNull(getActivity()).getBaseContext() != null) {
            preferenceHelper = new SharedPreferenceHelper(getActivity().getBaseContext());
        }
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, LENGTH_SHORT).show();
    }

    @Override
    public void showData(ProductsResponse model) {
        for (int i = 0; i < model.getProducts().size(); i++) {
            model.getProducts().get(i).setFavorite(preferenceHelper.idInBasket(model.getProducts().get(i).getId()));
        }
        productItemsAdapter.setData(model.getProducts());
        upperItemsAdapter.setData(model.getRelatedCategories());
    }

    @Override
    public void applyNewProducts(ArrayList<Products> products) {
        productItemsAdapter.setData(products);
    }

    @Override
    public void onFavoriteClicked(int productId, boolean isDelete) {
        if (isDelete) {
            preferenceHelper.removeFromFavorite(productId);
        } else {
            preferenceHelper.addToFavorite(productId);
        }
    }

    private void initSpinner() {

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sortItems, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBtn.setAdapter(spinnerAdapter);
        spinnerBtn.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String[] itemPosition = getResources().getStringArray(R.array.sortItems);
        String item = itemPosition[i];

        switch (item) {
            case "?????????????? ??????????????":
                presenter.fetchProducts(320, "popular", "desc", "N");
                break;
            case "?????????????? ??????????????":
            case "????????????????????":
                presenter.fetchProducts(320, "popular", "asc", "N");
                break;
            case "???? ??????????????":
                presenter.fetchProducts(320, "popular", "desc", "Y");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
