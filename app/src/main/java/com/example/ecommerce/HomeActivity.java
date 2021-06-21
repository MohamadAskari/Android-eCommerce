package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private String active_username;
    private ChipNavigationBar bottomNav;
    private FloatingActionButton fab;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setCurrentFragment(new HomeFragment());

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            active_username = (String) b.get("Active Username");
        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_add_product()).commit();
                fab.hide();
            }
        });

        dataBaseHelper = new DataBaseHelper(this);
        if(!dataBaseHelper.getClientByUsername(active_username).isSeller())
            fab.hide();

        bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment selectedFragment = null;

                switch (id) {
                    case R.id.home_menu:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.categories_menu:
                        selectedFragment = new CategoriesFragment();
                        break;
                    case R.id.favorites_menu:
                        selectedFragment = new FavoritesFragment();
                        break;
                }

                if (selectedFragment != null) {
                    setCurrentFragment(selectedFragment);
                    if(dataBaseHelper.getClientByUsername(active_username).isSeller())
                        fab.show();
                }
            }
        });
    }

    private void setCurrentFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        
    }

    public String getActiveUsername(){
        return active_username;
    }

    public void showFab(){
        fab.show();
    }
}