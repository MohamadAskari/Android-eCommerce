package com.example.ecommerce.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ecommerce.InCategory.CategoryUtils;
import com.example.ecommerce.Main.MainActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.RecyclerViewAdapter;
import com.example.ecommerce.R;
import com.example.ecommerce.ProfileMenu.SpinnerOptionsActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

//    private String active_username;
    private EditText search_bar;
    private CircleImageView dp_profile;
//    private ImageSlider imageSlider;
    private List<Product> productList = new ArrayList<>();
    private List<Product> promotedProducts = new ArrayList<>();
    private List<Product> EverySingleProduct;
    private TextView emptyListView_tv, promoted_tv, based_on_your_interest_tv;
    private RelativeLayout promoted_rl;
    DataBaseHelper dataBaseHelper;
    private RecyclerView recyclerView, recyclerViewPromoted;
    private RecyclerViewAdapter mAdapter, promotedAdapter;
//    private RecyclerView.LayoutManager layoutManager;
    private AlertDialog.Builder builder;
    private Client ActiveClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CategoryUtils.setIsInHomeFragment();

//        imageSlider = view.findViewById(R.id.image_slider);
//        setImageSlider(imageSlider);

        ActiveClient = Client.getActive_client();

        dataBaseHelper = new DataBaseHelper(getActivity());
        productList = dataBaseHelper.getAllProducts();
        promotedProducts = dataBaseHelper.getAllPromotedProducts();

        EverySingleProduct = dataBaseHelper.getAllProducts();

        promoted_tv = view.findViewById(R.id.tv_promoted);
        promoted_rl = view.findViewById(R.id.promoted_rl);
        based_on_your_interest_tv =view.findViewById(R.id.based_on_your_interest_tv);
        emptyListView_tv = view.findViewById(R.id.empty_lv);
        emptyListView_tv.setVisibility(View.INVISIBLE);
        if (promotedProducts.isEmpty())
            emptyListView_tv.setVisibility(View.VISIBLE);

        search_bar = view.findViewById(R.id.search_bar_main);
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setEverythingVisible();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setEverythingInvisible();
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                if(search_bar.getText().toString().trim().equals(""))
                    setEverythingVisible();
            }
        });

        /*dp_profile = view.findViewById(R.id.profile_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.profile_dropdown_items, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dp_profile.setAdapter(adapter);
        dp_profile.setSelection(0);
        dp_profile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view).setText(null);
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("Log out")){
                    builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.ic_baseline_warning_24);
                    builder.setTitle(R.string.confirm_log_out_title);
                    builder.setMessage(R.string.confirm_log_out_message);
                    builder.setPositiveButton(R.string.confirm_log_out, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
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
                    dp_profile.setSelection(0);
                }
                else if(!item.equals("Select")) {
                    Intent intent = new Intent(getActivity(), SpinnerOptionsActivity.class);
                    intent.putExtra("Selected Item", item);
//                    intent.putExtra("Active User", ActiveClient);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        dp_profile = view.findViewById(R.id.profile_dropdown);
        dp_profile.setImageURI(ActiveClient.getImageUrl());
        dp_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getContext(), dp_profile);
                popup.inflate(R.menu.profile_dropdown_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String selected_item = null;
                        switch (item.getItemId()) {
                            case R.id.edit_profile_item:
                                selected_item = "Edit your profile";
                                break;
                            case R.id.manage_products_item:
                                selected_item = "Manage your products";
                                break;
                            case R.id.setting_item:
                                selected_item = "Setting";
                                break;
                            default : // case R.id.log_out_item:
                                builder = new AlertDialog.Builder(getActivity());
                                builder.setIcon(R.drawable.ic_baseline_warning_24);
                                builder.setTitle(R.string.confirm_log_out_title);
                                builder.setMessage(R.string.confirm_log_out_message);
                                builder.setPositiveButton(R.string.confirm_log_out, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
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
                                break;
                        }
                        if(selected_item != null){
                            Intent intent = new Intent(getActivity(), SpinnerOptionsActivity.class);
                            intent.putExtra("Selected Item", selected_item);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        recyclerViewPromoted = view.findViewById(R.id.lv_promoted);
        promotedAdapter = new RecyclerViewAdapter(promotedProducts, getActivity());
        recyclerViewPromoted.setAdapter(promotedAdapter);
        recyclerViewPromoted.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPromoted.setLayoutManager(linearLayoutManager);
        recyclerViewPromoted.setItemAnimator(new DefaultItemAnimator());

        recyclerView = view.findViewById(R.id.lv_productList);
        mAdapter = new RecyclerViewAdapter(productList, getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
//        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onResume() {

        dataBaseHelper = new DataBaseHelper(getActivity());
        productList = dataBaseHelper.getInterestedProducts(Client.getActive_client());
        promotedProducts = dataBaseHelper.getAllPromotedProducts();

        promotedAdapter = new RecyclerViewAdapter(promotedProducts, getActivity());
        recyclerViewPromoted.setAdapter(promotedAdapter);

        search_bar.setText("");

        mAdapter = new RecyclerViewAdapter(productList, getActivity());
        recyclerView.setAdapter(mAdapter);
        super.onResume();
    }

//    private void setImageSlider(ImageSlider imageSlider) {
//        List<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel(R.drawable.image1, null, ScaleTypes.CENTER_CROP));
//        slideModels.add(new SlideModel(R.drawable.image2, null, ScaleTypes.CENTER_CROP));
//        slideModels.add(new SlideModel(R.drawable.image3, null, ScaleTypes.CENTER_CROP));
//        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
//    }
//
    private void filter(String text){

        ArrayList<Product> filteredList = new ArrayList<>();

        if (text.equalsIgnoreCase("")){
            for (Product item : productList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            mAdapter.filterList(filteredList);
        }
        else {
            for (Product item : EverySingleProduct) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            mAdapter.filterList(filteredList);
        }

    }

    private void setEverythingVisible(){
        promoted_tv.setVisibility(View.VISIBLE);
        promoted_rl.setVisibility(View.VISIBLE);
        based_on_your_interest_tv.setVisibility(View.VISIBLE);
    }

    private void setEverythingInvisible(){
        promoted_tv.setVisibility(View.GONE);
        promoted_rl.setVisibility(View.GONE);
        based_on_your_interest_tv.setVisibility(View.GONE);
    }
}