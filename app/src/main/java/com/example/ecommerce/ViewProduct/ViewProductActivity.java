package com.example.ecommerce.ViewProduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

public class ViewProductActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private Button viewSellerInfo_btn;
    private ImageView back_btn, product_pic;
    private TextView product_name, product_price, product_category, product_subcategory, product_description;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    // pop up components
    private TextView seller_username, seller_phonenumber;
    private ImageView seller_profile_pic, view_seller_info_back_icon;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        product_pic = findViewById(R.id.view_product_image);
        product_category = findViewById(R.id.view_product_category);
        product_subcategory = findViewById(R.id.view_product_subcategory);
        product_name = findViewById(R.id.view_product_name);
        product_price = findViewById(R.id.view_product_price);
        product_description = findViewById(R.id.view_product_description);

        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");
        product_name.setText(product.getName());
        String price = product.getPrice() + "$";
        product_price.setText(price);
        product_description.setText(product.getDescription());
        product_category.setText(product.getCategory());
        product_subcategory.setText(product.getSubCategory());
        product.setImageOnImageView(product_pic);


        toggleButton = findViewById(R.id.add_favorite_icon);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_24));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_border_24));
            }
        });
        back_btn = findViewById(R.id.view_product_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewSellerInfo_btn = findViewById(R.id.view_seller_info_btn);
        viewSellerInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSellerInfoDialog(product.getSellerUsername());
            }
        });

    }

    public void showSellerInfoDialog(String sellerUsername){
        dialogBuilder = new AlertDialog.Builder(this);
        final View viewSellerInfo_popupView = getLayoutInflater().inflate(R.layout.popup_view_seller_info, null);
        dataBaseHelper = new DataBaseHelper(this);

        view_seller_info_back_icon = viewSellerInfo_popupView.findViewById(R.id.view_seller_info_back_icon);
        seller_username = viewSellerInfo_popupView.findViewById(R.id.seller_username);
        seller_phonenumber = viewSellerInfo_popupView.findViewById(R.id.seller_phonenumber);
        seller_profile_pic = viewSellerInfo_popupView.findViewById(R.id.seller_profile_pic);

        Client seller = dataBaseHelper.getClientByUsername(sellerUsername);
        seller_username.setText(seller.getUserName());
        seller_phonenumber.setText(seller.getPhoneNumber());
        // seller_profile_pic.setImageOnImageView()

        dialogBuilder.setView(viewSellerInfo_popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        view_seller_info_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}