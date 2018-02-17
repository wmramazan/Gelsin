package com.gelsin.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gelsin.android.R;
import com.gelsin.android.item.OrderItem;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {

    private Context context;
    private ArrayList<OrderItem> orders;

    public OrderListAdapter(Context context, ArrayList<OrderItem> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public OrderListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderListViewHolder holder, final int position) {
        final OrderItem item = orders.get(position);

    }

    public void deleteItem(int index) {
        orders.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, orders.size());
    }

    @Override
    public int getItemCount() {
        return (null != orders ? orders.size() : 0);
    }

    public static class OrderListViewHolder extends RecyclerView.ViewHolder {

        protected TextView title, text, date;

        public OrderListViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.item_order_title);
            this.text = itemView.findViewById(R.id.item_order_text);
            this.date = itemView.findViewById(R.id.item_order_date);
        }
    }

}
