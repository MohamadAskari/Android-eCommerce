package com.example.ecommerce.Bridge;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Home.HomeFragment;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetProfilePicFragment extends Fragment {

    private Client ActiveClient;
    private AppCompatButton set_pic_camera, set_pic_gallery;
    private TextView next_fragment_tv;
    private CircleImageView profile_pic;
    private Uri imageUri;
    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    public static final int All_PERMS_CODE = 1101;
    private String currentPhotoPath;
    private String next;
    boolean hasImage;
    DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_profile_pic, container, false);

        ActiveClient = Client.getActive_client();
        set_pic_camera = view.findViewById(R.id.set_profile_camera_btn);
        set_pic_gallery = view.findViewById(R.id.set_profile_gallery_btn);
        next_fragment_tv = view.findViewById(R.id.set_profile_skip_tv);
        profile_pic = view.findViewById(R.id.profile_image);
        dataBaseHelper = new DataBaseHelper(getActivity());
        hasImage = false;
        next = getResources().getString(R.string.next);

        set_pic_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });

        set_pic_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_int = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery_int, GALLERY_REQUEST_CODE);
            }
        });

        next_fragment_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasImage){
                    boolean set = dataBaseHelper.setProfilePic(ActiveClient);
                    if (set)
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new ExplanationFragment()).commit();
                    else
                        Toast.makeText(getActivity(), "Failed to set photo, try again", Toast.LENGTH_SHORT).show();
                }
                else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_bridge, new ExplanationFragment()).commit();
                    ActiveClient.setImageUrl(Uri.parse("default"));
                    boolean set = dataBaseHelper.setProfilePic(ActiveClient);
                }
            }
        });

        return view;
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
                profile_pic.setImageURI(imageUri);
                ActiveClient.setImageUrl(imageUri);
                next_fragment_tv.setText(next);
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
                profile_pic.setImageURI(imageUri);
                ActiveClient.setImageUrl(imageUri);
                next_fragment_tv.setText(next);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

}