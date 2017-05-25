package com.example.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmailEditText;
    EditText loginPasswordEditText;
    Button login;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailEditText = (EditText) findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancelButtonInLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmailEditText.setError(null);
                loginPasswordEditText.setError(null);
                final String EMAIL_REGEX = "^(.+)@(.+)$";
                if ((loginEmailEditText.getText().toString().length() <= 0) || (!(Pattern.matches(EMAIL_REGEX, loginEmailEditText.getText().toString())))) {
                    loginEmailEditText.setError("Required valid email id");
                    loginEmailEditText.requestFocus();
                }else if (loginPasswordEditText.getText().toString().length() < 1) {
                    loginPasswordEditText.setError("Required");
                    loginPasswordEditText.requestFocus();
                }else {
                    Toast.makeText(LoginActivity.this,"Login successful!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,LoginOrSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
