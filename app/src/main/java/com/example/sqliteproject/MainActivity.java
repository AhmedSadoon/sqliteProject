package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName,etUserName,etPassword;
    SQLiteClass db;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        etName=findViewById(R.id.etName);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        imageView=findViewById(R.id.ivImage);

        db=new SQLiteClass(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                db.insertData(etName.getText().toString(),etUserName.getText().toString(),etPassword.getText().toString());

                MainActivity.this.startActivity(intent);
            }
        });



    }

}
