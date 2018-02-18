package com.gelsin.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gelsin.android.adapter.OrderListAdapter;
import com.gelsin.android.item.CustomerItem;
import com.gelsin.android.item.OrderItem;
import com.gelsin.android.util.RecyclerTouchListener;
import com.gelsin.android.util.ResultHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ShopOrdersFragment extends Fragment {

    private View view;
    private Context context;
    private RecyclerView orderList;
    private ArrayList<OrderItem> orders;
    private OrderListAdapter orderListAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_item, container, false);
        context = getContext();

        orderList = view.findViewById(R.id.itemList);
        progressBar = view.findViewById(R.id.itemList_progress);

        orders = new ArrayList<>();
        //orders.add(new OrderItem("test", "test", "test", 12.21, 123.12));

        GelsinActions.getShopOrders(new ResultHandler() {
            @Override
            public void handle(String result) {

                Gson gson = new Gson();
                orders = gson.fromJson(result, new TypeToken<ArrayList<OrderItem>>(){}.getType());

                if(orders.size() == 0) {
                    TextView noContent_title = view.findViewById(R.id.itemList_noContent_title);
                    TextView noContent_text = view.findViewById(R.id.itemList_noContent_text);
                    noContent_title.setText(R.string.no_orders_title);
                    noContent_text.setText(R.string.no_shop_orders_text);
                    view.findViewById(R.id.itemList_noContent).setVisibility(View.VISIBLE);
                } else {
                    orderList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    orderListAdapter = new OrderListAdapter(context, orders);
                    orderList.setAdapter(orderListAdapter);
                }

                progressBar.setVisibility(View.GONE);

            }
        });

        orderList.addOnItemTouchListener(new RecyclerTouchListener(context, orderList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, DeliveryActivity.class);
                CustomerItem customer = orders.get(position).getCustomer();
                intent.putExtra("order_id", orders.get(position).get_id());
                intent.putExtra("customer_name", customer.getName());
                intent.putExtra("latitude", customer.getLatitude());
                intent.putExtra("longitude", customer.getLongitude());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }
}
