package com.example.ecommerce.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class AddProductFragment extends Fragment {

    private Spinner parent_spinner, child_spinner;
    private List<String> subcategories = new ArrayList<>();
    private EditText inputproductname, inputproductprice, inputproductdescription;
    private Button submit_btn, take_photo_btn, from_gallery_btn;
    private ImageView product_pic, back_btn;
    private Uri product_pic_url;
    private Client ActiveClient;
    private boolean hasImage;
    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    public static final int All_PERMS_CODE = 1101;
    private String currentPhotoPath;
    DataBaseHelper dataBaseHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

//        ActiveClient = ((HomeActivity)getActivity()).getActiveClient();

        ActiveClient = Client.getActive_client();

        parent_spinner = view.findViewById(R.id.parent_spinner);
        child_spinner = view.findViewById(R.id.child_spinner);
        inputproductname = view.findViewById(R.id.product_name);
        inputproductprice = view.findViewById(R.id.product_price);
        inputproductdescription = view.findViewById(R.id.product_description);
        submit_btn = view.findViewById(R.id.submit_btn);
        dataBaseHelper = new DataBaseHelper(getActivity());
        hasImage = false;

        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Fashion");
        categories.add("Sports");
        categories.add("Home");
        categories.add("Motors");
        categories.add("Real Estate");
        categories.add("Entertainment");

        ArrayAdapter<String> adapter_1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        adapter_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parent_spinner.setAdapter(adapter_1);
        parent_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Electronics")) {
                    subcategories.clear();
                    subcategories.add("Smart phones & Tablets");
                    subcategories.add("PC & Laptops");
                    subcategories.add("TV & Smart TV");
                    subcategories.add("Smart watches");
                    subcategories.add("Accessories");
                    subcategories.add("Audio");
                    subcategories.add("Gaming consoles");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Fashion")){
                    subcategories.clear();
                    subcategories.add("Women clothing");
                    subcategories.add("Women shoes");
                    subcategories.add("Men clothing");
                    subcategories.add("Men shoes");
                    subcategories.add("Kids clothing");
                    subcategories.add("Kids shoes");
                    subcategories.add("Handbags");
                    subcategories.add("Jewelery");
                    subcategories.add("Watches");
                    subcategories.add("Accessories");
                    subcategories.add("Others");

                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Sports")){
                    subcategories.clear();
                    subcategories.add("Fitness");
                    subcategories.add("Running");
                    subcategories.add("Hunting");
                    subcategories.add("Winter sports");
                    subcategories.add("Water sports");
                    subcategories.add("Martial arts");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Home")){
                    subcategories.clear();
                    subcategories.add("Furniture");
                    subcategories.add("Kitchen");
                    subcategories.add("Smart home");
                    subcategories.add("Yard & Garden");
                    subcategories.add("Tools");
                    subcategories.add("Home decoration");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Motors")){
                    subcategories.clear();
                    subcategories.add("Cars");
                    subcategories.add("Motorcycles");
                    subcategories.add("Tools");
                    subcategories.add("Vehicle parts");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Real Estate")){
                    subcategories.clear();
                    subcategories.add("Office");
                    subcategories.add("Department");
                    subcategories.add("Store");
                    subcategories.add("Villa");
                    subcategories.add("Others");
                    fillSpinner();
                }
                else if(parent.getItemAtPosition(position).equals("Entertainment")){
                    subcategories.clear();
                    subcategories.add("Camping equipment");
                    subcategories.add("Toys");
                    subcategories.add("Pets");
                    subcategories.add("Collectibles & Art");
                    subcategories.add("Musical instruments");
                    subcategories.add("Video games");
                    subcategories.add("Books & Magazine");
                    subcategories.add("Others");
                    fillSpinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        super.onCreate(savedInstanceState);

        product_pic = view.findViewById(R.id.product_image);
        take_photo_btn = view.findViewById(R.id.takephoto_btn);
        from_gallery_btn = view.findViewById(R.id.fromgallery_btn);

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

        back_btn = view.findViewById(R.id.add_product_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                ((HomeActivity)getActivity()).showFab();
            }
        });

        submit_btn.setOnClickListener(v -> submitProduct());

        return view;
    }

    private void askCameraPermission() {
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

    @Override
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
                product_pic_url = Uri.fromFile(f);
                product_pic.setImageURI(product_pic_url);
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
                product_pic_url = contentUri;
                product_pic.setImageURI(product_pic_url);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getActivity().getContentResolver();
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

    private void submitProduct() {

        String product_name = inputproductname.getText().toString();
        String product_price = inputproductprice.getText().toString();
        String product_description = inputproductdescription.getText().toString();
        String product_category = parent_spinner.getSelectedItem().toString();
        String product_subCategory = child_spinner.getSelectedItem().toString();
        String product_seller = ActiveClient.getPhoneNumber();

        if(TextUtils.isEmpty(product_name) || TextUtils.isEmpty(product_price)){
            Toast.makeText(getActivity(), "Please fill out all required fields", Toast.LENGTH_LONG).show();
        }
        else if(product_pic.getDrawable() == null || !hasImage)
            Toast.makeText(getActivity(), "Please set a photo for your product", Toast.LENGTH_SHORT).show();
        else {
            Product product;
            if(TextUtils.isEmpty(product_description))
                product = new Product(product_pic_url, product_name, product_price, "", product_category, product_subCategory, product_seller);
            else
                product = new Product(product_pic_url, product_name, product_price, product_description, product_category, product_subCategory, product_seller);

            boolean succeed = dataBaseHelper.addProduct(product);

            if(succeed) {
                Client client = dataBaseHelper.getClientByPhonenumber(product_seller);
                int product_count = client.getProduct_count() + 1;
                boolean updatedProductCount = dataBaseHelper.updateProductCount(client, String.valueOf(product_count));
                if (updatedProductCount) {
                    Toast.makeText(getActivity(), "Product added successfully ", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    ((HomeActivity)getActivity()).showFab();
                }
                else
                    Toast.makeText(getActivity(), "Task failed, try again", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_LONG).show();
        }

    }

    public void fillSpinner(){

        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subcategories);
        adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        child_spinner.setAdapter(adapter_2);
    }
}
