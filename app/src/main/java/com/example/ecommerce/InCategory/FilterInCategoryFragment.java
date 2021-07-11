package com.example.ecommerce.InCategory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ecommerce.R;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


public class FilterInCategoryFragment extends Fragment {

    private RangeSlider price_slider;
    private EditText price_from_value, price_to_value;
    private TextView tv_select_subcategory;
    private List<CheckBox> subCategoriesCheckboxes = new ArrayList<>();
    private CheckBox only_with_image_checkbox;
    private RadioButton mostExpensive_rb, cheapest_rb;
    private String category;
    private Button apply_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter_in_category, container, false);

        tv_select_subcategory = view.findViewById(R.id.tv_select_subcategory);
        category = CategoryUtils.getSelectedCategoryTitle();

        String text = "In " + category + " :";
        tv_select_subcategory.setText(text);

        // sub category checkboxes
        LinearLayout linearLayout = view.findViewById(R.id.checkBox_linear_layout);
        createSubCategoriesCheckbox(linearLayout);

        price_slider = view.findViewById(R.id.price_slider);
        price_slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @NotNull
            @Override
            public String getFormattedValue(float value) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                currencyFormat.setCurrency(Currency.getInstance("USD"));
                return currencyFormat.format(value);
            }
        });
        price_slider.setValueFrom(0);
        price_slider.setValueTo(CategoryUtils.getMaxPrice());
        price_slider.setValues((float)(0), (float)CategoryUtils.getMaxPrice());

        price_from_value = view.findViewById(R.id.price_slider_from_value);
        price_to_value = view.findViewById(R.id.price_slider_to_value);
        price_from_value.setText(String.valueOf((int)price_slider.getValueFrom()));
        price_to_value.setText(String.valueOf((int)price_slider.getValueTo()));

        price_slider.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull @NotNull RangeSlider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull @NotNull RangeSlider slider) {
                price_from_value.setText(String.valueOf(Math.round(price_slider.getValues().get(0))));
                price_to_value.setText(String.valueOf(Math.round(price_slider.getValues().get(1))));
            }
        });

        only_with_image_checkbox = view.findViewById(R.id.only_with_image_checkbox);

        mostExpensive_rb = view.findViewById(R.id.rb_most_expensive);
        cheapest_rb = view.findViewById(R.id.rb_cheapest);

        apply_btn = view.findViewById(R.id.apply_filter_btn);
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyFilter();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,
                        R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.in_category_fragment_container, new InCategoryProductsFragment()).commit();
            }
        });

        return view;
    }

    private void createSubCategoriesCheckbox(LinearLayout linearLayout){
        subCategoriesCheckboxes.clear();
        List<String> subCategories = CategoryUtils.getAllSubCategoriesInCategory(category);
        for(String subCategory : subCategories){
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(subCategory);
            checkBox.setTextSize(20);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (linearLayout != null) {
                linearLayout.addView(checkBox);
                subCategoriesCheckboxes.add(checkBox);
            }
        }

    }
    private List<String> getSelectedSubCategories(List<CheckBox> checkBoxes){
        List<String> selectedSubCategories = new ArrayList<>();
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                selectedSubCategories.add(checkBox.getText().toString());
            }
        }
        return selectedSubCategories;
    }
    private void applyFilter(){
        CategoryUtils.filterCategoryProducts(getSelectedSubCategories(subCategoriesCheckboxes), Integer.parseInt(price_from_value.getText().toString()),
                Integer.parseInt(price_to_value.getText().toString()), only_with_image_checkbox.isChecked(), getSelectedSortType());
    }
    private String getSelectedSortType(){
        if(cheapest_rb.isChecked())
            return "cheapest";
        else if(mostExpensive_rb.isChecked())
            return "most expensive";
        else
            return "none";
    }
}