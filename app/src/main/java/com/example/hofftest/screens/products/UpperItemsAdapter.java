package com.example.hofftest.screens.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofftest.R;
import com.example.hofftest.data.RelatedCategory;

import java.util.ArrayList;
import java.util.List;

public class UpperItemsAdapter extends RecyclerView.Adapter<UpperItemsAdapter.UpperItemsHolder> {

    private ArrayList<RelatedCategory> items = new ArrayList<>();

    void setData(List<RelatedCategory> data) {
        this.items.clear();
        this.items.addAll(data);

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public UpperItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upper_product_item, parent, false);
        return new UpperItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpperItemsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class UpperItemsHolder extends RecyclerView.ViewHolder {

        private TextView upperItem;

        public UpperItemsHolder(@NonNull View itemView) {
            super(itemView);

            upperItem = itemView.findViewById(R.id.tv_upper_product);
        }

        void bind(RelatedCategory item) {
            upperItem.setText(item.getName());
        }
    }
}
