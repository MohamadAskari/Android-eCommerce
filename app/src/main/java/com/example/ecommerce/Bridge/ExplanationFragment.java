package com.example.ecommerce.Bridge;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.R;

public class ExplanationFragment extends Fragment {

    TextView next_tv, previous_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explanation, container, false);

        next_tv = view.findViewById(R.id.explanation_next_tv);
        previous_tv = view.findViewById(R.id.explanation_previous_tv);

        next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new SetInterestsFragment()).commit();
            }
        });

        previous_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new SetProfilePicFragment()).commit();
            }
        });

        return view;
    }
}