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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gelsin.android.adapter.ShopListAdapter;
import com.gelsin.android.item.ShopItem;
import com.gelsin.android.util.RecyclerTouchListener;
import com.gelsin.android.util.ResultHandler;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ShopsFragment extends Fragment {

    private View view;
    private Context context;
    private RecyclerView shopList;
    private ArrayList<ShopItem> shops;
    private ShopListAdapter shopListAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_item, container, false);
        context = getContext();

        shopList = view.findViewById(R.id.itemList);
        progressBar = view.findViewById(R.id.itemList_progress);

        if(null == MapFragment.nearbyShops)
            shops = new ArrayList<>();
        else
            shops = MapFragment.nearbyShops;
        //shops.add(new ShopItem("test", "test", "test", 12.21, 123.12));

        if(shops.size() == 0) {
            TextView noContent_title = view.findViewById(R.id.itemList_noContent_title);
            TextView noContent_text = view.findViewById(R.id.itemList_noContent_text);
            noContent_title.setText(R.string.no_nearby_shops_title);
            noContent_text.setText(R.string.no_nearby_shops_text);
            view.findViewById(R.id.itemList_noContent).setVisibility(View.VISIBLE);
        } else {
            shopList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            shopListAdapter = new ShopListAdapter(context, shops);
            shopList.setAdapter(shopListAdapter);
        }

        progressBar.setVisibility(View.GONE);

        shopList.addOnItemTouchListener(new RecyclerTouchListener(context, shopList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), OrderActivity.class)
                        .putExtra("shop_id", shops.get(position).get_id())
                        .putExtra("shop_name", shops.get(position).getName()));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

}
