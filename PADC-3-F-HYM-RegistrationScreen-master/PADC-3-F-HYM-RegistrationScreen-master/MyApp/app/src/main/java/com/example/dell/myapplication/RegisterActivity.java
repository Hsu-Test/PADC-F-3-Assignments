package com.example.dell.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Dell on 11/12/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;

    }

    private TextView btnBackToLogin;
    private EditText etName;
    private EditText etPhoneNumber;
    private EditText etEmailRegister;
    private EditText etPasswordRegister;
    private EditText etConfirmPasswordRegister;
    private RadioGroup rgGender;
    private EditText etAddress;
    private CheckBox cbAddress;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnBackToLogin = findViewById(R.id.btn_back_to_login);
        etName = findViewById(R.id.et_name);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etEmailRegister = findViewById(R.id.et_email_register);
        etPasswordRegister = findViewById(R.id.et_password_register);
        etConfirmPasswordRegister = findViewById(R.id.et_confirm_password_register);
        rgGender = findViewById(R.id.rg_gender);
        etAddress = findViewById(R.id.et_address);
        cbAddress = findViewById(R.id.cb_address);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG","Enter");
                String name = etName.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                String emailRegister = etEmailRegister.getText().toString();
                String passwordRegister = etPasswordRegister.getText().toString();
                String confirmPasswordRegister = etConfirmPasswordRegister.getText().toString();
                String address = etAddress.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    etName.setError("Name cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    etPhoneNumber.setError("Phone number cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(emailRegister)) {
                    etEmailRegister.setError("Email cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(passwordRegister)) {
                    etPasswordRegister.setError("Password cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(confirmPasswordRegister)) {
                    etConfirmPasswordRegister.setError("Confirm password cannot be empty");
                    return;
                }
                if (!passwordRegister.equals(confirmPasswordRegister)) {
                    etConfirmPasswordRegister.setError("Password and confirm password must be same");
                    return;
                }
                if (rgGender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please Choose the Gender", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(address)) {
                    etAddress.setError("Address cannot be empty");
                    return;
                }
                /*Snackbar.make(v, "Success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
                Intent intent= LoginActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= LoginActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });


    }
}