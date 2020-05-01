package com.example.sqliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, bRegistration;
    TextView tvRegister, etUserName, etPassword;
    SQLiteClass db;
    AlertDialog dialogreg;
    EditText etNameReg, etUsernameReg, etPasswordReg;
    String NameGet, UsernameGet, PasswordGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);

        tvRegister = findViewById(R.id.tvRegister);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);


        db = new SQLiteClass(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor_Resourses = db.getSelectedData(etUserName.getText().toString());
                while (cursor_Resourses.moveToNext()) {

                    NameGet = cursor_Resourses.getString(0);
                    UsernameGet = cursor_Resourses.getString(1);
                    PasswordGet = cursor_Resourses.getString(2);

                }

                if (PasswordGet.equals(etPassword.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("Name", NameGet);
                    intent.putExtra("UserName", UsernameGet);
                    LoginActivity.this.startActivity(intent);
                } else {

                    //تنبيه بسسيط وتكون محددة
                   /* AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this); //انشاء التنبيه
                    builder.setMessage("Your Password is Incorrect") // اعطاء نص
                            .setNegativeButton("Cancel",null)
                            .create() // انشاء التنبيه
                            .show(); // عرض

                    */

                    //ivphoto.setImageDrawable(getResources().getDrawable(R.drawable.f1));


                    final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    View adViewreg = getLayoutInflater().inflate(R.layout.activity_main, null);
                    etNameReg = adViewreg.findViewById(R.id.etName);
                    etUsernameReg = adViewreg.findViewById(R.id.etUserName);
                    etPasswordReg = adViewreg.findViewById(R.id.etPassword);
                    bRegistration = adViewreg.findViewById(R.id.btnRegister);

                    builder.setView(adViewreg).setTitle("Register").setCancelable(true);
                    dialogreg = builder.create();
                    builder.show();

                    bRegistration.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.insertData(etNameReg.getText().toString(),etUsernameReg.getText().toString(),etPasswordReg.getText().toString());
                        }
                    });






                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });


    }
}
