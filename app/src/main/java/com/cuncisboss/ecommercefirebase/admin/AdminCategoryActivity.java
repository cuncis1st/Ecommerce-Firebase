package com.cuncisboss.ecommercefirebase.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cuncisboss.ecommercefirebase.R;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView imgTShirts, imgSports, imgFemaleDresses, imgSweeters;
    private ImageView imgGlasses, imgPursesBags, imgHats, imgShoes;
    private ImageView imgHeadphoness, imgLaptops, imgWatches, imgMobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        initView();

        rowOne();
        rowTwo();
        rowThree();
    }

    private void initView() {
        imgTShirts = findViewById(R.id.img_t_shirts);
        imgSports = findViewById(R.id.img_sports);
        imgFemaleDresses = findViewById(R.id.img_female_dresses);
        imgSweeters = findViewById(R.id.img_sweeters);

        imgGlasses = findViewById(R.id.img_glasses);
        imgPursesBags = findViewById(R.id.img_purses_bags);
        imgHats = findViewById(R.id.img_hats);
        imgShoes = findViewById(R.id.img_shoes);

        imgHeadphoness = findViewById(R.id.img_headphoness);
        imgLaptops = findViewById(R.id.img_laptops);
        imgWatches = findViewById(R.id.img_watches);
        imgMobiles = findViewById(R.id.img_mobiles);
    }

    private void rowOne() {
        imgTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "tShirt");
                startActivity(i);
            }
        });

        imgSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "sports");
                startActivity(i);
            }
        });

        imgFemaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "femaleDresses");
                startActivity(i);
            }
        });

        imgSweeters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "sweeters");
                startActivity(i);
            }
        });
    }

    private void rowTwo() {
        imgGlasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "glasses");
                startActivity(i);
            }
        });

        imgPursesBags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "pursesBags");
                startActivity(i);
            }
        });

        imgHats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "hats");
                startActivity(i);
            }
        });

        imgShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "shoes");
                startActivity(i);
            }
        });
    }

    private void rowThree() {
        imgHeadphoness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "headphones");
                startActivity(i);
            }
        });

        imgLaptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "laptops");
                startActivity(i);
            }
        });

        imgWatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "watches");
                startActivity(i);
            }
        });

        imgMobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                i.putExtra("CATEGORY", "mobiles");
                startActivity(i);
            }
        });
    }

}



