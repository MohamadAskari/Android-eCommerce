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
import com.example.ecommerce.ProfileMenu.ManageProductFragment;
import com.example.ecommerce.R;
import com.example.ecommerce.ViewAndEditProduct.EditProductActivity;
import com.example.ecommerce.ViewAndEditProduct.ViewProductActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterForPromoted extends RecyclerView.Adapter<RecyclerViewAdapterForPromoted.MyViewHolder> {
    List<Product> productList;
    Context context;

    public RecyclerViewAdapterForPromoted(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_in_category_product, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapterForPromoted.MyViewHolder holder, int position) {
        holder.tv_product_name.setText(productList.get(position).getName());
        String price = productList.get(position).getPrice() + "$";
        holder.tv_product_price.setText(price);
        Glide.with(context).load(productList.get(position).getImageUrl()).into(holder.iv_product_image);

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

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv_product_image = itemView.findViewById(R.id.iv_product_image);
            tv_product_name = itemView.findViewById(R.id.tv_product_name);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            product_layout = itemView.findViewById(R.id.one_line_product_layout);
        }
    }
}
