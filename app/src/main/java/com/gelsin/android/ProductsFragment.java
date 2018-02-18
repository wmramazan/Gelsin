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

import com.gelsin.android.adapter.ProductListAdapter;
import com.gelsin.android.item.ProductItem;
import com.gelsin.android.util.RecyclerTouchListener;
import com.gelsin.android.util.ResultHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by wmramazan on 17.02.2018.
 */

public class ProductsFragment extends Fragment {

    private View view;
    private Context context;
    private RecyclerView productList;
    private ArrayList<ProductItem> products;
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_item, container, false);
        context = getContext();

        productList = view.findViewById(R.id.itemList);
        progressBar = view.findViewById(R.id.itemList_progress);

        products = new ArrayList<>();
        //products.add(new ProductItem("test", "test", "test", 12.21, 123.12));

        GelsinActions.getShopProducts(GelsinActions.SHOP_ID, new ResultHandler() {
            @Override
            public void handle(String result) {

                Gson gson = new Gson();
                products = gson.fromJson(result, new TypeToken<ArrayList<ProductItem>>(){}.getType());

                if(products.size() == 0) {
                    TextView noContent_title = view.findViewById(R.id.itemList_noContent_title);
                    TextView noContent_text = view.findViewById(R.id.itemList_noContent_text);
                    noContent_title.setText(R.string.no_products_title);
                    noContent_text.setText(R.string.no_products_text);
                    view.findViewById(R.id.itemList_noContent).setVisibility(View.VISIBLE);
                } else {
                    productList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    productListAdapter = new ProductListAdapter(context, products);
                    productList.setAdapter(productListAdapter);
                }

                progressBar.setVisibility(View.GONE);

            }
        });

        productList.addOnItemTouchListener(new RecyclerTouchListener(context, productList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("id", products.get(position).get_id());
                intent.putExtra("name", products.get(position).getName());
                intent.putExtra("price", String.valueOf(products.get(position).getPrice()));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }
    
}
