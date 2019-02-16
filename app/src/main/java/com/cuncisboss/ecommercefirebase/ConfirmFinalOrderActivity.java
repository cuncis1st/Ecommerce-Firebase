package com.cuncisboss.ecommercefirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuncisboss.ecommercefirebase.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText etName, etPhone, etAddress, etCity;
    private Button btnConfirm;

    private String totalPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalPrice = getIntent().getStringExtra("KEY_TOTAL");
        Toast.makeText(this, "Total Price: Rp." + totalPrice, Toast.LENGTH_SHORT).show();

        etName = findViewById(R.id.et_shipment_name);
        etPhone = findViewById(R.id.et_shipment_phone);
        etAddress = findViewById(R.id.et_shipment_address);
        etCity = findViewById(R.id.et_shipment_city);
        btnConfirm = findViewById(R.id.btn_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void check() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(this, "Please provide your full name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Toast.makeText(this, "Please provide your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etAddress.getText().toString())) {
            Toast.makeText(this, "Please provide your address...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etCity.getText().toString())) {
            Toast.makeText(this, "Please provide your city...", Toast.LENGTH_SHORT).show();
        } else {
            confirmOrder();
        }
    }

    private void confirmOrder() {
        String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());

        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("totalAmount", totalPrice);
        ordersMap.put("name", etName.getText().toString());
        ordersMap.put("phone", etPhone.getText().toString());
        ordersMap.put("address", etAddress.getText().toString());
        ordersMap.put("city", etCity.getText().toString());
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("state", "not shipped");

        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User View")
                            .child(Prevalent.currentOnlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ConfirmFinalOrderActivity.this, "Your final order has been placed successfully.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);       //user cannnot back to this activity again
                                    startActivity(i);
                                    finish();
                                }
                            });
                }
            }
        });

    }


}

