package com.example.ecommerce.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.InCategory.CategoryUtils;
import com.example.ecommerce.R;
import com.example.ecommerce.ViewProduct.ViewProductActivity;

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

        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_product_view, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.in_category_product_view, parent, false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

        if(CategoryUtils.isIsInHomeFragment()){
            return 0;
        }
        return 1;
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
                Intent intent = new Intent(context, ViewProductActivity.class);
                intent.putExtra("product", productList.get(position));
                context.startActivity(intent);
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
