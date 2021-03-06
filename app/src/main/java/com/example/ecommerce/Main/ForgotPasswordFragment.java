package com.example.ecommerce.Main;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import java.util.List;


public class ForgotPasswordFragment extends Fragment {

    private EditText username, password, confirm;
    private Button confirm_btn;
    private DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        username = view.findViewById(R.id.username_forgotpassword);
        password = view.findViewById(R.id.password_forgotpassword);
        confirm = view.findViewById(R.id.confirm_forgotpassword);
        confirm_btn = view.findViewById(R.id.confirm_button_forgotpassword);
        dataBaseHelper = new DataBaseHelper(getActivity());

        int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            confirm_btn.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.bg_button_night));
            username.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            password.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
            confirm.setBackground(AppCompatResources.getDrawable(getActivity(), R.drawable.white_outline));
        }

        ((MainActivity) getActivity()).showBackButton();

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputusername = username.getText().toString();
                String inputpassword = password.getText().toString();
                String inputconfirm = confirm.getText().toString();
                if(TextUtils.isEmpty(inputusername) || TextUtils.isEmpty(inputpassword) || TextUtils.isEmpty(inputconfirm)) {
                    Toast.makeText(getActivity(), "Please fill out all required fields", Toast.LENGTH_LONG).show();
                }
                else if(!inputpassword.equals(inputconfirm)) {
                    Toast.makeText(getActivity(), "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
                }
                else {
                    List<Client> all_clients = dataBaseHelper.getEveryClient();
                    boolean flag = true;
                    for(Client c : all_clients){
                        if(c.getUserName().equalsIgnoreCase(inputusername)){
                            boolean updated = dataBaseHelper.updatePassword(c, inputpassword);
                            if(updated){
                                Toast.makeText(getActivity(), "Password updated successfully", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(getActivity(), R.id.fragment_main).navigate(R.id.action_fragment_forgot_password_to_logInFragment);
                                ((MainActivity)getActivity()).hideBackButton();
                                ((MainActivity)getActivity()).showButtons();
                                flag = false;
                            }
                        }
                    }
                    if (flag)
                        Toast.makeText(getActivity(), "Invalid Username", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}