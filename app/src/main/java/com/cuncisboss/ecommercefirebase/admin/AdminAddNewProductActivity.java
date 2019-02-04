package com.cuncisboss.ecommercefirebase.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cuncisboss.ecommercefirebase.R;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private Button btnAddNewProduct;
    private EditText etProductName, etProductDesc, etProductPrice;
    private ImageView imgInputProduct;

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        initView();

        categoryName = getIntent().getStringExtra("CATEGORY");

        btnAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        btnAddNewProduct = findViewById(R.id.btn_new_product);
        etProductName = findViewById(R.id.et_product_name);
        etProductDesc = findViewById(R.id.et_product_desc);
        etProductPrice = findViewById(R.id.et_product_price);
        imgInputProduct = findViewById(R.id.select_product_image);
    }
}
