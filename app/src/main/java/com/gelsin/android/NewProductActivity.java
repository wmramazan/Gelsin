package com.gelsin.android;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class NewProductActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextInputLayout inputLayoutName, inputLayoutPrice;
    EditText inputName, inputPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        inputLayoutName = findViewById(R.id.new_product_name_layout);
        inputLayoutPrice = findViewById(R.id.new_product_price_layout);

        inputName = findViewById(R.id.new_product_name);
        inputPrice = findViewById(R.id.new_product_price);

        actionBar = getSupportActionBar();
    }

    public void requestFocus(View view) {
        if(view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void addProduct(View view) {

        if(inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.error_product_name));
            requestFocus(inputLayoutName);
            return;
        }

        if(inputPrice.getText().toString().trim().isEmpty()) {
            inputLayoutPrice.setError(getString(R.string.error_product_price));
            requestFocus(inputPrice);
            return;
        }

        inputLayoutName.setErrorEnabled(false);
        inputLayoutPrice.setErrorEnabled(false);

        //Add new product

    }
}
