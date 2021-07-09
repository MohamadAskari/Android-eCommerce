package com.example.ecommerce.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecommerce.Main.MainActivity;
import com.example.ecommerce.Model.Admin;
import com.example.ecommerce.R;

public class AdminActivity extends AppCompatActivity {

    AppCompatButton name_of_products_btn, name_of_sellers_btn, sum_of_prices_btn, top_seller_btn, users_login_count_btn, prioritize_products_btn, log_out_btn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        name_of_products_btn = findViewById(R.id.admin_name_of_products_btn);
        name_of_sellers_btn = findViewById(R.id.admin_name_of_sellers_btn);
        sum_of_prices_btn = findViewById(R.id.admin_sum_of_prices_btn);
        top_seller_btn = findViewById(R.id.admin_top_seller_btn);
        users_login_count_btn = findViewById(R.id.admin_users_login_count_btn);
        prioritize_products_btn = findViewById(R.id.admin_prioritize_products_btn);
        log_out_btn = findViewById(R.id.admin_log_out_btn);

        name_of_products_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameOfProductsDialog();
            }
        });

        name_of_sellers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameOfSellersDialog();
            }
        });

        sum_of_prices_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSumOfPricesDialog();
            }
        });

        top_seller_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopSellerDialog();
            }
        });

        users_login_count_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUsersLoginCountDialog();
            }
        });

        prioritize_products_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });



    }

    private void showUsersLoginCountDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
    }

    private void showTopSellerDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
    }

    private void showSumOfPricesDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
    }

    private void showNameOfSellersDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
    }

    private void showNameOfProductsDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
    }

    private void showLogoutDialog() {
        builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setTitle(R.string.confirm_log_out_title);
        builder.setMessage(R.string.confirm_log_out_message);
        builder.setPositiveButton(R.string.confirm_log_out, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.cancel_log_out, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DO NOTHING
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {

    }

}