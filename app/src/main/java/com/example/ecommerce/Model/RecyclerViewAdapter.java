package com.example.ecommerce.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.InCategory.CategoryUtils;
import com.example.ecommerce.R;
import com.example.ecommerce.ProfileMenu.ManageProductFragment;
import com.example.ecommerce.ViewAndEditProduct.EditProductActivity;
import com.example.ecommerce.ViewAndEditProduct.ViewProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Product> productList;
    Context context;

    public RecyclerViewAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType){
            case 0 : // HomeFragment
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_in_home_product, parent, false);
                break;
            case 1 : // InCategoryFragment
            case 2 : // FavoritesFragment
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_in_category_product, parent, false);
                break;
            default : // ManageProductsFragment : return 4
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_in_manage_products, parent, false);
                break;
        }

        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

        if (CategoryUtils.isInHomeFragment())
            return 0;
        else if (CategoryUtils.isInCategoryFragment())
            return 1;
        else if (CategoryUtils.isInFavoritesFragment())
            return 2;
        // else if (CategoryUtils.isIsInManageProductsFragment())
        return 3;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.tv_product_name.setText(productList.get(position).getName());
        String price = productList.get(position).getPrice() + "$";
        holder.tv_product_price.setText(price);
        Glide.with(context).load(productList.get(position).getImageUrl()).into(holder.iv_product_image);;

        holder.product_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fade fade = new Fade();
                View decor = ((Activity) context).getWindow().getDecorView();
                fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
                fade.excludeTarget(android.R.id.statusBarBackground, true);
                fade.excludeTarget(android.R.id.navigationBarBackground, true);
                ((Activity) context).getWindow().setEnterTransition(fade);
                ((Activity) context).getWindow().setExitTransition(fade);

                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("product", productList.get(position));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, holder.iv_product_image, ViewCompat.getTransitionName(holder.iv_product_image));
                context.startActivity(intent, options.toBundle());
            }
        });


        if(CategoryUtils.isInManageProductsFragment()){

            holder.dp_manage_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(context, holder.dp_manage_products);
                    popup.inflate(R.menu.manage_products_dropdown_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete_product_item:
                                    DataBaseHelper db = new DataBaseHelper(context);
                                    boolean removed = db.removeProduct(productList.get(position));
                                    if(removed){
                                        int product_count = Client.getActive_client().getProduct_count() - 1;
                                        db.updateProductCount(Client.getActive_client(), String.valueOf(product_count));
                                        Toast.makeText(context, "Product deleted successfully", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                    Toast.makeText(context, "Failed to delete product", Toast.LENGTH_LONG).show();
                                    }
                                    ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_spinner, new ManageProductFragment()).commit();
                                    return true;
                                case R.id.edit_product_item:
                                    Intent intent = new Intent(context, EditProductActivity.class);
                                    intent.putExtra("product", productList.get(position));
                                    context.startActivity(intent);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                }
            });
            holder.product_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filterList(ArrayList<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_product_image;
        TextView tv_product_name;
        TextView tv_product_price;
        ConstraintLayout product_layout;
        // In Manage Products Fragment
        ImageView dp_manage_products;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            iv_product_image = itemView.findViewById(R.id.iv_product_image);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            product_layout = itemView.findViewById(R.id.one_line_product_layout);
            if(CategoryUtils.isInManageProductsFragment())
                dp_manage_products = itemView.findViewById(R.id.manage_product_dropdown);
        }
    }
}
