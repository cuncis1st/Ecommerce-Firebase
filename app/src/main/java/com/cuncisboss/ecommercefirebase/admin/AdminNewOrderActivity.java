package com.cuncisboss.ecommercefirebase.admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cuncisboss.ecommercefirebase.R;
import com.cuncisboss.ecommercefirebase.model.AdminOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        recyclerView = findViewById(R.id.rv_cart_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrder> options = new FirebaseRecyclerOptions.Builder<AdminOrder>()
                .setQuery(ordersRef, AdminOrder.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrder, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrder, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, int position, @NonNull AdminOrder model) {
                        holder.userName.setText("Name: " + model.getName());
                        holder.userPhoneNumber.setText("Phone: " + model.getPhone());
                        holder.userTotalPrice.setText("Total Amount = Rp." + model.getTotalAmount());
                        holder.userDateTime.setText("Order at: " + model.getDate() + " " + model.getTime());
                        holder.userShippingAddress.setText("Shipping Address: " + model.getAddress() + ", " + model.getCity());
                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_layout, parent, false);

                        return new AdminOrdersViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userPhoneNumber, userTotalPrice, userDateTime, userShippingAddress;
        public Button btnShowOrders;

        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tv_order_username);
            userPhoneNumber = itemView.findViewById(R.id.tv_order_phone_number);
            userTotalPrice = itemView.findViewById(R.id.tv_order_total_price);
            userDateTime = itemView.findViewById(R.id.tv_order_dateTime);
            userShippingAddress = itemView.findViewById(R.id.tv_order_address);
            btnShowOrders = itemView.findViewById(R.id.btn_show_all_products);
        }
    }
}


























