package com.example.ecommerce.ViewAndEditProduct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Home.HomeActivity;
import com.example.ecommerce.Home.HomeFragment;
import com.example.ecommerce.Model.Client;
import com.example.ecommerce.Model.DataBaseHelper;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditProductActivity extends AppCompatActivity {

    private List<String> subcategories = new ArrayList<>();
    private EditText inputproductname, inputproductprice, inputproductdescription;
    private TextView tv_product_category, tv_product_subcategory;
    private Button apply_btn, cancel_btn, take_photo_btn, from_gallery_btn;
    private ImageView product_pic;
    private Uri product_pic_url;
    private Product product;
    private Client ActiveClient;
    private boolean hasImage;
    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    public static final int All_PERMS_CODE = 1101;
    private String currentPhotoPath;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Intent intent = getIntent();
        product = intent.getParcelableExtra("product");

        hasImage = product.hasImage();
        ActiveClient = Client.getActive_client();

        inputproductname = findViewById(R.id.edit_product_name);
        inputproductprice = findViewById(R.id.edit_product_price);
        inputproductdescription = findViewById(R.id.edit_product_description);
        tv_product_category = findViewById(R.id.edit_category_textView);
        tv_product_subcategory = findViewById(R.id.edit_subcategory_textView);
        apply_btn = findViewById(R.id.apply_changes_btn);
        cancel_btn = findViewById(R.id.cancel_changes_btn);
        dataBaseHelper = new DataBaseHelper(this);

        product_pic = findViewById(R.id.product_image);
        take_photo_btn = findViewById(R.id.takephoto_btn);
        from_gallery_btn = findViewById(R.id.fromgallery_btn);

        product_pic.setImageURI(product.getImageUrl());
        inputproductname.setText(product.getName());
        inputproductprice.setText(product.getPrice());
        inputproductdescription.setText(product.getDescription());
        tv_product_category.setText(product.getCategory());
        tv_product_subcategory.setText(product.getSubCategory());

        take_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
        from_gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery_int = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery_int, GALLERY_REQUEST_CODE);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        apply_btn.setOnClickListener(v -> applyChanges(product));
    }

    private void askCameraPermission() {
        // Camera and Storage permission on same time
        Log.d("TAG", "VerifyingPermission : Asking for permission ");


        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // index 0 = camera, index 1 = readStorage , index 2 = write Storage
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(), permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            dispatchTakePictureIntent();
        }
        else {
            ActivityCompat.requestPermissions(this, permissions, All_PERMS_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                product_pic_url = Uri.fromFile(f);
                product_pic.setImageURI(product_pic_url);
                product.setImageUrl(product_pic_url);
                hasImage = true;
                Log.d("tag", "Url Image : " + Uri.fromFile(f));

                // add pic to gallery
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                sendBroadcast(mediaScanIntent);
            }
        }
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                product_pic_url = contentUri;
                product_pic.setImageURI(product_pic_url);
                product.setImageUrl(product_pic_url);
                hasImage = true;
                Log.d("tag", "Uri Gallery Image : " + imageFileName);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private void applyChanges(Product product) {
        if(TextUtils.isEmpty(inputproductname.getText().toString().trim()) || TextUtils.isEmpty(inputproductprice.getText().toString().trim())){
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else {
            product.setName(inputproductname.getText().toString());
            product.setPrice(inputproductprice.getText().toString());
            product.setDescription(inputproductdescription.getText().toString());
            product.setHasImage(hasImage);
            boolean succeed = dataBaseHelper.updateProductValues(product);
            if(succeed) {
                Toast.makeText(this, "Product edited successfully ", Toast.LENGTH_LONG).show();
                finish();
            }
            else
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }

    }
}