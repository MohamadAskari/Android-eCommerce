package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import static java.security.AccessController.getContext;

public class ViewProductActivity extends AppCompatActivity {

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        toggleButton = findViewById(R.id.add_favorite_icon);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_24));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(ViewProductActivity.this, R.drawable.ic_baseline_favorite_border_24));
            }
        });

    }
}