package com.example.ecommerce.Bridge;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class SetInterestsFragment extends Fragment {

    private TextView next_tv, previous_tv;
    DataBaseHelper dataBaseHelper;
    private CheckBox electronics_checkbox, fashion_checkbox, sports_checkbox, home_checkbox, motors_checkbox,
            realstate_checkbox, entertainment_checkbox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_interests, container, false);

        next_tv = view.findViewById(R.id.set_interests_next_tv);
        previous_tv = view.findViewById(R.id.set_interests_previous_tv);

        electronics_checkbox = view.findViewById(R.id. electronics_checkbox);
        fashion_checkbox = view.findViewById(R.id. fashion_checkbox);
        sports_checkbox = view.findViewById(R.id. sports_checkbox);
        home_checkbox = view.findViewById(R.id. home_checkbox);
        motors_checkbox = view.findViewById(R.id. motors_checkbox);
        realstate_checkbox = view.findViewById(R.id. realstate_checkbox);
        entertainment_checkbox = view.findViewById(R.id. entertainment_checkbox);

        next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSelectedCategoriesToInterested();
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        previous_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new ExplanationFragment()).commit();
            }
        });

        return view;
    }

    private void addSelectedCategoriesToInterested() {
        dataBaseHelper = new DataBaseHelper(getActivity());
        Client active_user = Client.getActive_client();
        if(electronics_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user, "ELECTRONICS_TABLE");
        if(fashion_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"FASHION_TABLE");
        if(sports_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"SPORTS_TABLE");
        if(home_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"HOME_TABLE");
        if(motors_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"MOTORS_TABLE");
        if(realstate_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"REALESTATE_TABLE");
        if(entertainment_checkbox.isChecked())
            dataBaseHelper.addToInterestedCategories(active_user,"ENTERTAINMENT_TABLE");
    }
}