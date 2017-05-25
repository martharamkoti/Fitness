package com.example.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    RadioGroup genderGroup;
    Button signUp;
    Button cancelButton;
    char gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUp = (Button) findViewById(R.id.signUp);
        cancelButton = (Button) findViewById(R.id.cancelButtonInSignUp);

        genderGroup = (RadioGroup) findViewById(R.id.gender);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    gender = 'M';
                }
                if (checkedId == R.id.female) {
                    gender = 'F';
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEditText.setError(null);
                emailEditText.setError(null);
                passwordEditText.setError(null);

                final String EMAIL_REGEX = "^(.+)@(.+)$";
                final Pattern pattern2 = Pattern.compile("[^a-z A-Z]");
                final Boolean stringCheck1 = pattern2.matcher(nameEditText.getText().toString()).find();
                if ((nameEditText.getText().toString().length() == 0) || (stringCheck1)) {
                    nameEditText.setError("Required only alphabets");
                    nameEditText.requestFocus();
                } else if ((emailEditText.getText().toString().length() <= 0) || (!(Pattern.matches(EMAIL_REGEX, emailEditText.getText().toString())))) {
                    emailEditText.setError("Required valid email id");
                    emailEditText.requestFocus();
                } else if (genderGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(SignUpActivity.this, "Please provide your Gender", Toast.LENGTH_LONG).show();
                } else if (passwordEditText.getText().toString().length() < 5) {
                    passwordEditText.setError("Required six alphanumeric password");
                    passwordEditText.requestFocus();
                } else {
                    Toast.makeText(SignUpActivity.this,"SignUp successful!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LoginOrSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
