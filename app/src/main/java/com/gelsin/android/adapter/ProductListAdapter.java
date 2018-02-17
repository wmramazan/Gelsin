package com.gelsin.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gelsin.android.R;
import com.gelsin.android.item.ProductItem;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private Context context;
    private ArrayList<ProductItem> products;

    public ProductListAdapter(Context context, ArrayList<ProductItem> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductListViewHolder holder, final int position) {
        final ProductItem item = products.get(position);

        holder.name.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
    }

    public ProductItem getItem(int index) {
        return products.get(index);
    }

    public void deleteItem(int index) {
        products.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, products.size());
    }

    @Override
    public int getItemCount() {
        return (null != products ? products.size() : 0);
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {

        protected TextView name, price;

        public ProductListViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.item_product_name);
            this.price = itemView.findViewById(R.id.item_product_price);
        }
    }

}
