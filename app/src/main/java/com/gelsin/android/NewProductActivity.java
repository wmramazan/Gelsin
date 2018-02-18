package com.gelsin.android;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gelsin.android.util.ResultHandler;

public class NewProductActivity extends AppCompatActivity {

    ActionBar actionBar;
    TextInputLayout inputLayoutName, inputLayoutPrice;
    EditText inputName, inputPrice;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        inputLayoutName = findViewById(R.id.new_product_name_layout);
        inputLayoutPrice = findViewById(R.id.new_product_price_layout);
        inputName = findViewById(R.id.new_product_name);
        inputPrice = findViewById(R.id.new_product_price);
        progressBar = findViewById(R.id.new_product_progress);

        requestFocus(inputName);

        actionBar = getSupportActionBar();
    }

    public void requestFocus(View view) {
        if(view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void addProduct(final View view) {

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

        view.setEnabled(false);
        GelsinActions.addProduct(
                inputName.getText().toString(),
                Float.parseFloat(inputPrice.getText().toString()),
                new ResultHandler() {
                    @Override
                    public void handle(String result) {
                        progressBar.setVisibility(View.GONE);
                        view.setEnabled(true);

                        Toast.makeText(getApplicationContext(), R.string.successful_new_product, Toast.LENGTH_SHORT);
                        finish();
                    }
                }
        );
    }
}
