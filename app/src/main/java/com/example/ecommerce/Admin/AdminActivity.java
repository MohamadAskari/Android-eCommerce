package com.example.ecommerce.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerce.Main.MainActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private AppCompatButton name_of_products_btn, name_of_sellers_btn, sum_of_prices_btn, top_seller_btn, users_login_count_btn, prioritize_products_btn, log_out_btn;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);

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
        final View showUsersLoginCountPopup = getLayoutInflater().inflate(R.layout.popup_users_login_count, null);
        builder = new AlertDialog.Builder(AdminActivity.this);
        ImageView back_btn = showUsersLoginCountPopup.findViewById(R.id.login_count_back_icon);
        ListView listView = showUsersLoginCountPopup.findViewById(R.id.lv_login_count);
        List<Client> AllClients = dataBaseHelper.getEveryClient();

        if (AllClients.isEmpty()){
            ArrayList<String> text = new ArrayList<>();
            text.add("There are no users.");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminActivity.this, R.layout.text_no_users, text);
            listView.setAdapter(arrayAdapter);
        }
        else {

            ArrayList<String> ClientsLoginCount = new ArrayList<>();
            for (Client client : AllClients)
                ClientsLoginCount.add(client.showLoginCount());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, ClientsLoginCount);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showUsersLoginCountPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showTopSellerDialog() {
        final View showTopSellerPopup = getLayoutInflater().inflate(R.layout.popup_top_seller, null);
        builder = new AlertDialog.Builder(AdminActivity.this);
        ImageView back_btn = showTopSellerPopup.findViewById(R.id.top_seller_back_icon);
        TextView textView = showTopSellerPopup.findViewById(R.id.top_seller_tv);
        List<Client> AllSellers = dataBaseHelper.getAllSellers();
        if (AllSellers.isEmpty())
            textView.setText("There are no sellers.");
        else {
            Client TopSeller = AllSellers.get(0);
            int TopSellerProductCount = TopSeller.getProduct_count();
            for (int i = 1; i < AllSellers.size(); i++) {
                if (AllSellers.get(i).getProduct_count() >= TopSellerProductCount) {
                    TopSeller = AllSellers.get(i);
                    TopSellerProductCount = TopSeller.getProduct_count();
                }
            }

            Spanned durationSpanned;
            String formatted = "Top seller is " + "<b>" + TopSeller.getUserName() + "</b> " + " with total of  " + "<b>" + TopSeller.getProduct_count() + "</b>" + " products.";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                durationSpanned = Html.fromHtml(formatted, Html.FROM_HTML_MODE_LEGACY);
            } else {
                durationSpanned = Html.fromHtml(formatted);
            }
            textView.setText(durationSpanned);
        }
        builder.setView(showTopSellerPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showSumOfPricesDialog() {
        final View showSumOfPricesPopup = getLayoutInflater().inflate(R.layout.popup_sum_of_prices, null);
        builder = new AlertDialog.Builder(AdminActivity.this);
        ImageView back_btn = showSumOfPricesPopup.findViewById(R.id.sum_of_prices_back_icon);
        TextView textView = showSumOfPricesPopup.findViewById(R.id.sum_of_prices_tv);
        int sum = 0;

        List<Product> AllProducts = dataBaseHelper.getAllProducts();
        for(Product product : AllProducts)
            sum += Integer.parseInt(product.getPrice());

        textView.setText("Total worth of products is " + String.valueOf(sum) + "$.");
        builder.setView(showSumOfPricesPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showNameOfSellersDialog() {
        final View showNameOfSellersPopup = getLayoutInflater().inflate(R.layout.popup_name_of_sellers, null);
        builder = new AlertDialog.Builder(AdminActivity.this);
        ImageView back_btn = showNameOfSellersPopup.findViewById(R.id.name_of_sellers_back_icon);
        ListView listView = showNameOfSellersPopup.findViewById(R.id.lv_name_of_sellers);
        TextView TotalOfSellers_tv = showNameOfSellersPopup.findViewById(R.id.total_num_of_sellers);
        List<Client> AllSellers = dataBaseHelper.getAllSellers();

        if (AllSellers.isEmpty())
            TotalOfSellers_tv.setText("There are no sellers.");

        else {
            TotalOfSellers_tv.setText("Total of " + AllSellers.size() + " sellers.");

            ArrayList<String> SellersInformation = new ArrayList<>();
            for (Client seller : AllSellers)
                SellersInformation.add(seller.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, SellersInformation);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showNameOfSellersPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showNameOfProductsDialog() {
        final View showNameOfProductsPopup = getLayoutInflater().inflate(R.layout.popup_name_of_products, null);
        builder = new AlertDialog.Builder(AdminActivity.this);
        ImageView back_btn = showNameOfProductsPopup.findViewById(R.id.name_of_products_back_icon);
        ListView listView = showNameOfProductsPopup.findViewById(R.id.lv_name_of_products);
        TextView TotalOfProducts_tv = showNameOfProductsPopup.findViewById(R.id.total_num_of_products);
        List<Product> AllProducts = dataBaseHelper.getAllProducts();

        if (AllProducts.isEmpty())
            TotalOfProducts_tv.setText("There are no products.");
        else {
            TotalOfProducts_tv.setText("Total of " + AllProducts.size() + " products.");

            ArrayList<String> ProductsInformation = new ArrayList<>();
            for (Product product : AllProducts)
                ProductsInformation.add(product.toString());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, ProductsInformation);
            listView.setAdapter(arrayAdapter);
        }

        builder.setView(showNameOfProductsPopup);
        dialog = builder.create();
        dialog.show();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.dismiss();
            }
        });
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