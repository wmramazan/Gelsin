package com.gelsin.android;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gelsin.android.util.ResultHandler;

public class ProductActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextInputLayout inputLayoutName, inputLayoutPrice;
    EditText inputName, inputPrice;
    Button buttonRemove, buttonComplete;
    ProgressBar progressBar;
    Intent intent;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        
        intent = getIntent();

        inputLayoutName = findViewById(R.id.product_name_layout);
        inputLayoutPrice = findViewById(R.id.product_price_layout);
        inputName = findViewById(R.id.product_name);
        inputPrice = findViewById(R.id.product_price);
        progressBar = findViewById(R.id.product_progress);
        buttonRemove = findViewById(R.id.product_remove_button);
        buttonComplete = findViewById(R.id.product_complete_button);
        
        if(intent.hasExtra("name")) {
            isEdit = true;
            actionBar.setTitle(getString(R.string.edit_product));
            buttonComplete.setText(getString(R.string.edit_product));
            inputName.setText(intent.getStringExtra("name"));
            inputPrice.setText(intent.getStringExtra("price"));
            
            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 18.02.2018 Remove product
                }
            });
            buttonRemove.setVisibility(View.VISIBLE);
        }

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        requestFocus(inputName);

        actionBar = getSupportActionBar();
    }

    public void requestFocus(View view) {
        if(view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void addProduct() {

        if(inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.error_product_name));
            requestFocus(inputLayoutName);
            return;
        }

        inputLayoutName.setErrorEnabled(false);

        if(inputPrice.getText().toString().trim().isEmpty()) {
            inputLayoutPrice.setError(getString(R.string.error_product_price));
            requestFocus(inputPrice);
            return;
        }

        inputLayoutPrice.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);

        buttonComplete.setEnabled(false);
        if(isEdit) {
            GelsinActions.editProduct(
                    intent.getStringExtra("product_id"),
                    inputName.getText().toString(),
                    Float.parseFloat(inputPrice.getText().toString()),
                    new ResultHandler() {
                        @Override
                        public void handle(String result) {
                            progressBar.setVisibility(View.GONE);
                            buttonComplete.setEnabled(true);

                            Toast.makeText(getApplicationContext(), R.string.successful_edit_product, Toast.LENGTH_SHORT);
                            finish();
                        }
                    }
            );
        } else {
            GelsinActions.addProduct(
                    inputName.getText().toString(),
                    Float.parseFloat(inputPrice.getText().toString()),
                    new ResultHandler() {
                        @Override
                        public void handle(String result) {
                            progressBar.setVisibility(View.GONE);
                            buttonComplete.setEnabled(true);

                            Toast.makeText(getApplicationContext(), R.string.successful_new_product, Toast.LENGTH_SHORT);
                            finish();
                        }
                    }
            );
        }
    }
}
