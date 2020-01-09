package com.example.dell.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dell on 11/11/2017.
 */

public class LoginActivity extends AppCompatActivity {
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;

    }
    private static final String CORRECT_EMAIL="abc@gmail.com";
    private static final String CORRECT_PASSWORD="Abcd"; //constant attribute
    private Button btnLogin;
    private EditText etEmailOrPhone;
    private EditText etPassword;
    private TextView btnForgetPassword;
    private TextView btnSaleRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmailOrPhone=findViewById(R.id.et_email_or_phone);
        etPassword=findViewById(R.id.et_password);
        btnForgetPassword=findViewById(R.id.btn_forget_password);
        btnSaleRegister=findViewById(R.id.btn_sale_register);
        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String emailOrPhone= etEmailOrPhone.getText().toString();
               String password=etPassword.getText().toString();
                if(TextUtils.isEmpty(emailOrPhone)){
              //if(emailOrPhone==null){
                   etEmailOrPhone.setError("Email or phone cannot be empty");
                   return;
               }
               if(TextUtils.isEmpty(password)){
              //if(password==null){
                   etPassword.setError("Password cannot be emptty");
                   return;
               }
               if(TextUtils.equals(emailOrPhone,CORRECT_EMAIL) && TextUtils.equals(password,CORRECT_PASSWORD)){
                  // Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent intent= HelloAndroidActivity.newIntent(getApplicationContext());
                    startActivity(intent);
               }
               else{

                   Snackbar.make(view,"Incorrect email or password,please try again",
                           Snackbar.LENGTH_INDEFINITE).show();

               }
              /*  Snackbar.make(view, "Login Button Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

            }
        });
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"\"Forgot Password\" is comming soon.",Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        btnSaleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Snackbar.make(v,"Registeration screen is comming soon",Snackbar.LENGTH_INDEFINITE).show();
                Intent intent= RegisterActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }


}
