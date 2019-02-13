package com.cuncisboss.ecommercefirebase;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.cuncisboss.ecommercefirebase.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

//    private FloatingActionButton fabAddToCart;
    private ImageView imgProductDetail;
    private ElegantNumberButton btnNumber;
    private TextView tvProductName, tvProductDesc, tvPorductPrice;
    private String productID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("pid");

//        fabAddToCart = findViewById(R.id.fab_add_to_cart);
        imgProductDetail = findViewById(R.id.img_product_detail);
        btnNumber = findViewById(R.id.btn_number);
        tvProductName = findViewById(R.id.tv_product_name);
        tvProductDesc = findViewById(R.id.tv_product_description);
        tvPorductPrice = findViewById(R.id.tv_product_price);


        getProductDetails(productID);

    }

    private void getProductDetails(String productID) {
        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products");

        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Product products = dataSnapshot.getValue(Product.class);

                    tvProductName.setText(products.getPname());
                    tvProductDesc.setText(products.getDescription());
                    tvPorductPrice.setText(products.getPrice());
                    Picasso.get().load(products.getImage()).into(imgProductDetail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
