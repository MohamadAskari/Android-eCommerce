package com.example.ecommerce.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {

    private ChipNavigationBar bottomNav;
    private FloatingActionButton fab;
    private DataBaseHelper dataBaseHelper;
    private Client ActiveClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dataBaseHelper = new DataBaseHelper(this);
        ActiveClient = Client.getActive_client();
        ActiveClient.setImageUrl(dataBaseHelper.getProfileUri(ActiveClient.getPhoneNumber()));

        setCurrentFragment(new HomeFragment(), ActiveClient);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddProductFragment()).commit();
                fab.hide();
            }
        });

        if(!dataBaseHelper.getClientByPhonenumber(ActiveClient.getPhoneNumber()).isSeller())
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
                    setCurrentFragment(selectedFragment, ActiveClient);
                    if(dataBaseHelper.getClientByPhonenumber(ActiveClient.getPhoneNumber()).isSeller())
                        fab.show();
                }
            }
        });
    }

    private void setCurrentFragment(Fragment fragment, Client client){
//        Bundle bundle = sendData(client);
//        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

//    private Bundle sendData(Client client) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("Active User", client);
//        return bundle;
//    }

    @Override
    public void onBackPressed() {
        
    }

    public Client getActiveClient() { return ActiveClient; }

    public void showFab(){
        fab.show();
    }
}