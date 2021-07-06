package com.example.ecommerce.Bridge;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.R;

public class SetInterestsFragment extends Fragment {

    TextView next_tv, previous_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_interests, container, false);

        next_tv = view.findViewById(R.id.set_interests_next_tv);
        previous_tv = view.findViewById(R.id.set_interests_previous_tv);


        //////TO DO


        next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}