package com.example.ecommerce.Spinner;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.R;

public class SettingFragment extends Fragment {

    private SwitchCompat switchCompat;
    private ImageView back_btn_image_view;
    private TextView FAQ_tv, contactUs_tv, removeFavs_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        switchCompat = view.findViewById(R.id.night_mode_switch);
        back_btn_image_view = view.findViewById(R.id.view_setting_back_icon);
        removeFavs_tv = view.findViewById(R.id.remove_favs_tv);
        contactUs_tv = view.findViewById(R.id.contact_us_tv);
        FAQ_tv = view.findViewById(R.id.FAQS_tv);
        Client ActiveClient = (Client) this.getArguments().getSerializable("Active User");

        back_btn_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("Active Username", ActiveClient.getUserName());
                intent.putExtra("Is Seller", ActiveClient.isSeller());
                intent.putExtra("Active User", ActiveClient);
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

        removeFavs_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        contactUs_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        FAQ_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return view;
    }
}