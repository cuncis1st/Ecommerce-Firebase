package com.cuncisboss.ecommercefirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuncisboss.ecommercefirebase.model.User;
import com.cuncisboss.ecommercefirebase.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    EditText etPhone, etPassword;
    Button btnLogin;
    ProgressDialog loadingBar;

    private String parentDbName = "Users";
    private CheckBox cb_rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Paper.init(this);

        etPhone = findViewById(R.id.et_phone_number_input);
        etPassword = findViewById(R.id.et_password_input);
        btnLogin = findViewById(R.id.btn_login);
        loadingBar = new ProgressDialog(this);

        cb_rememberMe = findViewById(R.id.remember_me_checkbox);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            allowAccessToAccount(phone, password);
        }
    }

    private void allowAccessToAccount(final String phone, final String password) {
        if (cb_rememberMe.isChecked()) {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    User usersData = dataSnapshot.child(parentDbName).child(phone).getValue(User.class);
                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Logged in successfully...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with " + phone + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}






















