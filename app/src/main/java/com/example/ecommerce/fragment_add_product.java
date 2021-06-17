package com.example.ecommerce;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class fragment_add_product extends Fragment {

    Spinner parent_spinner, child_spinner;
    List<String> subcategories = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        parent_spinner = view.findViewById(R.id.parent_spinner);
        child_spinner = view.findViewById(R.id.child_spinner);

        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Fashion");
        categories.add("Sports");
        categories.add("Home");
        categories.add("Motors");
        categories.add("Real State");
        categories.add("Entertainment");

        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parent_spinner.setAdapter(adapter_1);
        parent_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Electronics")) {
                    subcategories.clear();
                    subcategories.add("1");
                    subcategories.add("2");
                    subcategories.add("3");
                    subcategories.add("4");
                    subcategories.add("5");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Fashion")){
                    subcategories.clear();
                    subcategories.add("6");
                    subcategories.add("7");
                    subcategories.add("8");
                    subcategories.add("9");
                    subcategories.add("10");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Sports")){
                    subcategories.clear();
                    subcategories.add("11");
                    subcategories.add("12");
                    subcategories.add("13");
                    subcategories.add("14");
                    subcategories.add("15");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Home")){
                    subcategories.clear();
                    subcategories.add("16");
                    subcategories.add("17");
                    subcategories.add("18");
                    subcategories.add("19");
                    subcategories.add("20");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Motors")){
                    subcategories.clear();
                    subcategories.add("21");
                    subcategories.add("22");
                    subcategories.add("23");
                    subcategories.add("24");
                    subcategories.add("25");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Real State")){
                    subcategories.clear();
                    subcategories.add("26");
                    subcategories.add("27");
                    subcategories.add("28");
                    subcategories.add("29");
                    subcategories.add("30");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Entertainment")){
                    subcategories.clear();
                    subcategories.add("31");
                    subcategories.add("32");
                    subcategories.add("33");
                    subcategories.add("34");
                    subcategories.add("35");
                    fillSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    public void fillSpinner(){

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subcategories);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        child_spinner.setAdapter(adapter_2);
    }
}
