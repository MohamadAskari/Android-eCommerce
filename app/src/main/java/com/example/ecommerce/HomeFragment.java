package com.example.ecommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Spinner profile_spinner;
    ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.image_slider);
        setImageSlider(imageSlider);

        profile_spinner = view.findViewById(R.id.profile_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.profile_dropdown_items, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        profile_spinner.setAdapter(adapter);
        profile_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view).setText(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    private void setImageSlider(ImageSlider imageSlider) {
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, null, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image2, null, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.image3, null, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
    }

}