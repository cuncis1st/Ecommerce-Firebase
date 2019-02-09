package com.cuncisboss.ecommercefirebase.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuncisboss.ecommercefirebase.R;
import com.cuncisboss.ecommercefirebase.interfaces.ItemClickListener;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvProductName, tvProductDesc, tvProductPrice;
    public ImageView imgProduct;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        tvProductName = itemView.findViewById(R.id.tv_product_name);
        tvProductDesc = itemView.findViewById(R.id.tv_product_desc);
        tvProductPrice = itemView.findViewById(R.id.tv_product_price);
        imgProduct = itemView.findViewById(R.id.img_product);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
