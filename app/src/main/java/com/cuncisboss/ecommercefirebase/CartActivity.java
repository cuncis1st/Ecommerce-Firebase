package com.cuncisboss.ecommercefirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cuncisboss.ecommercefirebase.model.Cart;
import com.cuncisboss.ecommercefirebase.prevalent.Prevalent;
import com.cuncisboss.ecommercefirebase.viewholder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnNextProcess;
    private TextView tvTotalPrice, tvCartMessage;

    private int overTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.rv_cart_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnNextProcess = findViewById(R.id.btn_next_process);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvCartMessage = findViewById(R.id.tv_cart_message);

        btnNextProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTotalPrice.setText("Total Price: Rp." + String.valueOf(overTotalPrice));

                Intent i = new Intent(CartActivity.this, ConfirmFinalOrderActivity.class);
                i.putExtra("KEY_TOTAL", String.valueOf(overTotalPrice));
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkOrderState();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                        .child("Products"), Cart.class)
                .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {
                holder.tvProductName.setText(model.getPname());
                holder.tvProductPrice.setText("Rp. " + model.getPrice());
                holder.tvProductQuantity.setText("Quantity = " + model.getQuantity());

                int oneTypeProductPrice = ((Integer.valueOf(model.getPrice())) * Integer.valueOf(model.getQuantity()));
                overTotalPrice = overTotalPrice + oneTypeProductPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence[] options = new CharSequence[]{
                                "Edit",
                                "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Options: ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent i = new Intent(CartActivity.this, ProductDetailsActivity.class);
                                    i.putExtra("pid", model.getPid());
                                    startActivity(i);
                                }
                                if (which == 1) {
                                    cartListRef.child("User View")
                                            .child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(CartActivity.this, "Item successfully removed.", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(CartActivity.this, HomeActivity.class);
                                                        startActivity(i);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
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

    private void checkOrderState() {
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String shippingState = dataSnapshot.child("state").getValue().toString();
                    String userName = dataSnapshot.child("name").getValue().toString();

                    if (shippingState.equals("shipped")) {
                        tvTotalPrice.setText("Dear " + userName + "\n order is shipped successfully.");
                        recyclerView.setVisibility(View.GONE);
                        tvCartMessage.setVisibility(View.VISIBLE);
                        btnNextProcess.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "You can purchase more products, once you received your first final orders", Toast.LENGTH_SHORT).show();
                    } else if (shippingState.equals("not shipped")) {
                        tvTotalPrice.setText("Shipping State = Not Shipped");
                        recyclerView.setVisibility(View.GONE);
                        tvCartMessage.setVisibility(View.VISIBLE);
                        btnNextProcess.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "You can purchase more products, once you received your first final orders", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

















