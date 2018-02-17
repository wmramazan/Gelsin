package com.gelsin.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gelsin.android.R;
import com.gelsin.android.item.ShopItem;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> {

    private Context context;
    private ArrayList<ShopItem> shops;

    public ShopListAdapter(Context context, ArrayList<ShopItem> shops) {
        this.context = context;
        this.shops = shops;
    }

    @Override
    public ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopListViewHolder holder, final int position) {
        final ShopItem item = shops.get(position);

        holder.name.setText(item.getName());
        // TODO: 17.02.2018 Add distance info
        holder.info.setText(item.getCategory());
    }

    public void deleteItem(int index) {
        shops.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, shops.size());
    }

    @Override
    public int getItemCount() {
        return (null != shops ? shops.size() : 0);
    }

    public static class ShopListViewHolder extends RecyclerView.ViewHolder {

        protected TextView name, info;

        public ShopListViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.item_shop_name);
            this.info = itemView.findViewById(R.id.item_shop_info);
        }
    }
    
}
