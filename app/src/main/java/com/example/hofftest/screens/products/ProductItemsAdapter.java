package com.example.hofftest.screens.products;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofftest.R;
import com.example.hofftest.data.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductItemsAdapter extends RecyclerView.Adapter<ProductItemsAdapter.ProductItemsHolder> {

    private ArrayList<Products> items = new ArrayList<>();
    private OnButtonListener onButtonListener;

    public ProductItemsAdapter(OnButtonListener listener) {
        onButtonListener = listener;
    }

    void setData(List<Products> data) {
        this.items.clear();
        this.items.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProductItemsHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName, productNewPrice, productOldPrice, productStatus, review;
        private Button favoriteBtn;
        private AppCompatRatingBar rating;


        public ProductItemsHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.iv_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            productNewPrice = itemView.findViewById(R.id.tv_price);
            productOldPrice = itemView.findViewById(R.id.tv_old_price);
            productStatus = itemView.findViewById(R.id.tv_product_status);
            favoriteBtn = itemView.findViewById(R.id.btn_favorite);
            rating = itemView.findViewById(R.id.rating);
            review = itemView.findViewById(R.id.number_of_review);
        }

        void bind(Products item) {

            Picasso
                    .get()
                    .load(item.getImage())
                    .into(productImage);


            productName.setText(item.getName());
            productStatus.setText(item.getStatus());
            rating.setRating(item.getRating());
            review.setText(String.valueOf("(" + item.getNumberOfReviews() + ")"));
            productOldPrice.setText(String.valueOf(item.getPrices().getOldPrice()) + " ₽");
            productNewPrice.setText(String.valueOf(item.getPrices().getNewPrice()) + " ₽");
            productOldPrice.setPaintFlags(productOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            favoriteBtn.setSelected(item.getFavorite());


            favoriteBtn.setOnClickListener(view -> {
                onButtonListener.onFavoriteClicked(item.getId(), item.getFavorite());
                item.setFavorite(!item.getFavorite());
                notifyItemChanged(getAdapterPosition(), null);
            });
        }
    }
}
