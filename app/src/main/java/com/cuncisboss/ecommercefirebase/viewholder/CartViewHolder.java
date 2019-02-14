package com.cuncisboss.ecommercefirebase.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cuncisboss.ecommercefirebase.R;
import com.cuncisboss.ecommercefirebase.interfaces.ItemClickListener;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvProductName, tvProductPrice, tvProductQuantity;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        tvProductName = itemView.findViewById(R.id.tv_cart_product_name);
        tvProductPrice = itemView.findViewById(R.id.tv_cart_product_price);
        tvProductQuantity = itemView.findViewById(R.id.tv_cart_product_quantity);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
