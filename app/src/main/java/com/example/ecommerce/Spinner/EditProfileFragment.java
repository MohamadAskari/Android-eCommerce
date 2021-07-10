package com.example.ecommerce.Spinner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    private CircleImageView circleImageView;
    private AppCompatButton changePic_btn, changePass_btn, confirm_btn, dialog_camera_btn, dialog_gallery_btn, dialog_confirm_btn;
    private ImageView back_btn_image_view;
    private EditText firstName_et, lastName_et, username_et, email_et, phone_et, dialog_new_pass_et, dialog_confirm_pass_et;
    private Uri imageUri;
    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    public static final int All_PERMS_CODE = 1101;
    private String currentPhotoPath;
    boolean hasImage;
    private DataBaseHelper dataBaseHelper;
    private List<Client> AllClients;
    private Client ActiveClient;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        circleImageView = view.findViewById(R.id.change_picture_image_view);
        changePic_btn = view.findViewById(R.id.change_picture_btn);
        changePass_btn = view.findViewById(R.id.change_password_btn);
        confirm_btn = view.findViewById(R.id.edit_profile_confirm_btn);
        back_btn_image_view = view.findViewById(R.id.view_edit_back_icon);
        firstName_et = view.findViewById(R.id.edit_firstname_et);
        lastName_et = view.findViewById(R.id.edit_lastname_et);
        username_et = view.findViewById(R.id.edit_username_et);
        email_et = view.findViewById(R.id.edit_email_et);
        phone_et = view.findViewById(R.id.edit_phone_number_et);
        dataBaseHelper = new DataBaseHelper(getActivity());
        hasImage = false;
        AllClients = dataBaseHelper.getEveryClient();

        ActiveClient = Client.getActive_client();

        String imagePah = ActiveClient.getImagePath();
        if(!imagePah.equalsIgnoreCase("default"))
            circleImageView.setImageURI(ActiveClient.getImageUrl());

        firstName_et.setText(ActiveClient.getFirstName());
        lastName_et.setText(ActiveClient.getLastName());
        username_et.setText(ActiveClient.getUserName());
        email_et.setText(ActiveClient.getEmail());
        phone_et.setText(ActiveClient.getPhoneNumber());
        phone_et.setFocusable(false);
        phone_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Your phone number cannot be changed", Toast.LENGTH_SHORT).show();
            }
        });

        back_btn_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("Active Username", ActiveClient.getUserName());
                intent.putExtra("Is Seller", ActiveClient.isSeller());
//                intent.putExtra("Active User", ActiveClient);
                startActivity(intent);

            }
        });

        changePic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePicDialog();
            }
        });

        changePass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePassDialog();
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = firstName_et.getText().toString();
                String lastname = lastName_et.getText().toString();
                String username = username_et.getText().toString();
                String email = email_et.getText().toString();
                String phonenumber = phone_et.getText().toString();
                String imagepath = ActiveClient.getImagePath();

                if(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(username)
                        || TextUtils.isEmpty(email) || TextUtils.isEmpty(phonenumber))
                    Toast.makeText(getActivity(), "Please don't leave any field empty", Toast.LENGTH_SHORT).show();
                else {
                    boolean updated = dataBaseHelper.updateClientValues(ActiveClient, firstname, lastname, username, email, phonenumber, imagepath);
                    if (updated){
                        Toast.makeText(getActivity(), "Applied changes successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        Client UpdatedClient = dataBaseHelper.getClientByPhonenumber(phonenumber);
//                        intent.putExtra("Active User", UpdatedClient);
                        Client.setActive_client(UpdatedClient);
                        startActivity(intent);
                    }
                }
            }
        });

        username_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    boolean flag = false;
                    for(Client client : AllClients){
                        if(client.getUserName().equalsIgnoreCase(username_et.getText().toString()) && !client.getUserName().equalsIgnoreCase(ActiveClient.getUserName())) {
                            flag = true;
                        }
                    }
                    if(flag){
                        String warning = getResources().getString(R.string.username_taken);
                        SpannableString string2 = new SpannableString(warning);
                        string2.setSpan(new ForegroundColorSpan(Color.RED), 0, warning.length(), 0 );
                        Toast.makeText(getActivity(), string2,Toast.LENGTH_LONG).show();
                        confirm_btn.setEnabled(false);
                    }
                    else
                        confirm_btn.setEnabled(true);
                }
            }
        });

        return view;
    }

    private void showChangePassDialog() {
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View changePassView = getLayoutInflater().inflate(R.layout.popup_change_pass, null);

        dialog_confirm_btn = changePassView.findViewById(R.id.confirm_button_changepassword);
        dialog_new_pass_et = changePassView.findViewById(R.id.password_changepassword);
        dialog_confirm_pass_et = changePassView.findViewById(R.id.confirm_changepassword);

        dialogBuilder.setView(changePassView);
        dialog = dialogBuilder.create();
        dialog.show();


        dialog_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputpassword = dialog_new_pass_et.getText().toString();
                String inputconfirm = dialog_confirm_pass_et.getText().toString();

                if(TextUtils.isEmpty(inputpassword) || TextUtils.isEmpty(inputconfirm)) {
                    Toast.makeText(getActivity(), "Please fill out all required fields", Toast.LENGTH_LONG).show();
                }
                else if(!inputpassword.equals(inputconfirm)) {
                    Toast.makeText(getActivity(), "Your password and confirmation password do not match", Toast.LENGTH_LONG).show();
                }
                else {
                    boolean updated = dataBaseHelper.updatePassword(ActiveClient, inputpassword);
                    if (updated)
                        Toast.makeText(getActivity(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Failed to update password, try again", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }
            }
        });
    }

    private void showChangePicDialog() {
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View changePicView = getLayoutInflater().inflate(R.layout.popup_change_pic, null);

        dialog_camera_btn = changePicView.findViewById(R.id.change_pic_camera_btn);
        dialog_gallery_btn = changePicView.findViewById(R.id.change_pic_gallery_btn);

        dialogBuilder.setView(changePicView);
        dialog = dialogBuilder.create();
        dialog.show();

        dialog_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
                dialog.dismiss();
            }
        });
        dialog_gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_int = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery_int, GALLERY_REQUEST_CODE);
                dialog.dismiss();
            }
        });
    }

    private void askCameraPermission() {
        /*if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }
        else {
            dispatchTakePictureIntent();
        }*/

        // Camera and Storage permission on same time
        Log.d("TAG", "VerifyingPermission : Asking for permission ");


        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // index 0 = camera, index 1 = readStorage , index 2 = write Storage
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            dispatchTakePictureIntent();
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), permissions, All_PERMS_CODE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg"
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                File f = new File(currentPhotoPath);
                imageUri = Uri.fromFile(f);
                circleImageView.setImageURI(imageUri);
                ActiveClient.setImageUrl(imageUri);
                hasImage = true;
                Log.d("tag", "Url Image : " + Uri.fromFile(f));

                // add pic to gallery
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                getActivity().sendBroadcast(mediaScanIntent);
            }
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d("tag", "Uri Gallery Image : " + imageFileName);
                hasImage = true;
                imageUri = contentUri;
                circleImageView.setImageURI(imageUri);
                ActiveClient.setImageUrl(imageUri);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


}