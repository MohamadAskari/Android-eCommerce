package com.example.ecommerce.ViewAndEditProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

public class EditProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");
        // To Do

    }
}