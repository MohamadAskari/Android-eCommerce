package com.example.ecommerce.ProfileMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

import java.util.List;

public class SettingFragment extends Fragment {

    private SwitchCompat switchCompat;
    private ImageView back_btn_image_view, back_btn_popup_faq, back_btn_popup_contact_us;
    private AppCompatButton FAQ_btn, contactUs_btn, removeFavs_btn;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private DataBaseHelper dataBaseHelper;
    private List<Product> allFavProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        switchCompat = view.findViewById(R.id.night_mode_switch);
        back_btn_image_view = view.findViewById(R.id.view_setting_back_icon);
        removeFavs_btn = view.findViewById(R.id.remove_favs_btn);
        contactUs_btn = view.findViewById(R.id.contact_us_btn);
        FAQ_btn = view.findViewById(R.id.FAQS_btn);
        Client ActiveClient = Client.getActive_client();
        String clientPhonenumber = ActiveClient.getPhoneNumber();
        dataBaseHelper = new DataBaseHelper(getActivity());

        back_btn_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("Active Username", ActiveClient.getUserName());
                intent.putExtra("Is Seller", ActiveClient.isSeller());
//                intent.putExtra("Active User", ActiveClient);
                startActivity(intent);
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        removeFavs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean removed = false;
                String phonenumber = ActiveClient.getPhoneNumber();
                allFavProducts = dataBaseHelper.getAddedToFavoritesProducts(phonenumber);
                for(Product product : allFavProducts)
                    removed = dataBaseHelper.removeProductFromFavorites(product, phonenumber);
                if (removed)
                    Toast.makeText(getActivity(), "Products removed from favorites", Toast.LENGTH_SHORT).show();
            }
        });

        contactUs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow("Contact us");
            }
        });

        FAQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow("FAQ");
            }
        });

        return view;
    }
    public void showPopupWindow(String Case){

        dialogBuilder = new AlertDialog.Builder(getActivity());
        View viewFAQorContactUsPopup = null;

        switch (Case){
            case "Contact us":{
                viewFAQorContactUsPopup  = getLayoutInflater().inflate(R.layout.popup_contact_us, null);
                back_btn_popup_contact_us = viewFAQorContactUsPopup.findViewById(R.id.popup_contact_us_back_icon);
                back_btn_popup_contact_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            }
            case "FAQ":{
                viewFAQorContactUsPopup  = getLayoutInflater().inflate(R.layout.popup_faq, null);
                back_btn_popup_faq = viewFAQorContactUsPopup.findViewById(R.id.popup_faq_back_icon);
                back_btn_popup_faq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            }
        }
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(viewFAQorContactUsPopup);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}