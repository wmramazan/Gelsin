package com.gelsin.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gelsin.android.adapter.ProductListAdapter;
import com.gelsin.android.item.ProductItem;
import com.gelsin.android.util.RecyclerTouchListener;
import com.gelsin.android.util.ResultHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ShopProductsActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView productList;
    private ArrayList<ProductItem> products;
    private ProductListAdapter productListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_products);

        intent = getIntent();

        productList = findViewById(R.id.shop_products);
        progressBar = findViewById(R.id.shop_products_progress);

        products = new ArrayList<>();
        GelsinActions.getShopProducts(intent.getStringExtra("shop_id"), new ResultHandler() {
            @Override
            public void handle(String result) {

                Gson gson = new Gson();
                products = gson.fromJson(result, new TypeToken<ArrayList<ProductItem>>(){}.getType());

                if(products.size() == 0) {
                    findViewById(R.id.shop_no_products).setVisibility(View.VISIBLE);
                } else {
                    productList.setLayoutManager(new LinearLayoutManager(ShopProductsActivity.this, LinearLayoutManager.VERTICAL, false));
                    productListAdapter = new ProductListAdapter(ShopProductsActivity.this, products);
                    productList.setAdapter(productListAdapter);
                }

                progressBar.setVisibility(View.GONE);

            }
        });

        productList.addOnItemTouchListener(new RecyclerTouchListener(this, productList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
