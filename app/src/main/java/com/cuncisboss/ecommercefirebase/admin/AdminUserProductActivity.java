package com.cuncisboss.ecommercefirebase.admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuncisboss.ecommercefirebase.R;
import com.cuncisboss.ecommercefirebase.model.Cart;
import com.cuncisboss.ecommercefirebase.viewholder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUserProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference cartListRef;

    private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_product);

        userID = getIntent().getStringExtra("KEY_UID");

        recyclerView = findViewById(R.id.rv_product_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartListRef = FirebaseDatabase.getInstance().getReference()
                .child("Cart List").child("Admin View").child(userID).child("Products");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef, Cart.class)
                .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                holder.tvProductName.setText(model.getPname());
                holder.tvProductPrice.setText("Rp. " + model.getPrice());
                holder.tvProductQuantity.setText("Quantity = " + model.getQuantity());
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_layout, parent, false);

                return new CartViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}



















