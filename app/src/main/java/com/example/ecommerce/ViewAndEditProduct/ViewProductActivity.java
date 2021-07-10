package com.example.ecommerce.ViewAndEditProduct;

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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProductActivity extends AppCompatActivity {

    private Client active_client;
    private ToggleButton toggleButton;
    private Button viewSellerInfo_btn;
    private ImageView back_btn, product_pic;
    private TextView product_name, product_price, product_category, product_subcategory, product_description;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    // pop up components
    private TextView seller_username, seller_phonenumber;
    private ImageView view_seller_info_back_icon;
    private CircleImageView seller_profile_pic;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        dataBaseHelper = new DataBaseHelper(this);

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

        active_client = Client.getActive_client();

        toggleButton = findViewById(R.id.add_favorite_icon);
        if(!dataBaseHelper.isProductInFavorites(product, active_client.getPhoneNumber())){
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24));
            toggleButton.setChecked(false);
        }
        else{
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24));
            toggleButton.setChecked(true);
        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_24));
                    boolean succeed = dataBaseHelper.addProductToFavorites(product, active_client.getPhoneNumber());
                    if(succeed)
                        Toast.makeText(ViewProductActivity.this, "added successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_border_24));
                    boolean succeed = dataBaseHelper.removeProductFromFavorites(product, active_client.getPhoneNumber());
                    if(succeed)
                        Toast.makeText(ViewProductActivity.this, "removed successfully", Toast.LENGTH_LONG).show();
                }
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
                showSellerInfoDialog(product.getSellerPhonenumber());
            }
        });

    }

    public void showSellerInfoDialog(String sellerPhonenumber){
        dialogBuilder = new AlertDialog.Builder(this);
        final View viewSellerInfo_popupView = getLayoutInflater().inflate(R.layout.popup_view_seller_info, null);

        view_seller_info_back_icon = viewSellerInfo_popupView.findViewById(R.id.view_seller_info_back_icon);
        seller_username = viewSellerInfo_popupView.findViewById(R.id.seller_username);
        seller_phonenumber = viewSellerInfo_popupView.findViewById(R.id.seller_phonenumber);
        seller_profile_pic = viewSellerInfo_popupView.findViewById(R.id.seller_profile_pic);

        Client seller = dataBaseHelper.getClientByPhonenumber(sellerPhonenumber);
        seller_username.setText(seller.getUserName());
        seller_phonenumber.setText(seller.getPhoneNumber());
        seller_profile_pic.setImageURI(seller.getImageUrl());

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