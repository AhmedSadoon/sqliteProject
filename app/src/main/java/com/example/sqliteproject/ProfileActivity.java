package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Intent intent;
    String Name,Username;
    TextView tvName,tvUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName=findViewById(R.id.tvName);
        tvUserName=findViewById(R.id.tvUserName);

        intent=getIntent();
        Name=intent.getStringExtra("Name");
        Username=intent.getStringExtra("UserName");

        tvName.setText("Name: "+Name);
        tvUserName.setText("User Name :"+Username);

    }
}
