package com.example.fitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AccountSettingsActivity extends AppCompatActivity {

    EditText nameEditText,heightEditText,weightEditText,goalWeight,goalDate;
    Button saveUpdates,cancelActivity1;
    char gender;
    RadioGroup genderGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        goalWeight = (EditText) findViewById(R.id.goalWeight);
        goalDate = (EditText) findViewById(R.id.goalDate);
        saveUpdates = (Button) findViewById(R.id.saveUpdates);
        cancelActivity1 = (Button) findViewById(R.id.cancelActivity1);

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

        saveUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().length() == 0) {
                    nameEditText.setError("Required");
                    nameEditText.requestFocus();
                } else if (heightEditText.getText().toString().length() == 0) {
                    heightEditText.setError("Required");
                    heightEditText.requestFocus();
                } else if (weightEditText.getText().toString().length() == 0) {
                    weightEditText.setError("Required");
                    weightEditText.requestFocus();
                } else if (genderGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(AccountSettingsActivity.this, "Please provide your Gender", Toast.LENGTH_LONG).show();
                } else if (goalWeight.getText().toString().length() == 0) {
                    goalWeight.setError("Required");
                    goalWeight.requestFocus();
                } else if (goalDate.getText().toString().length() == 0) {
                    goalDate.setError("Required");
                    goalDate.requestFocus();
                } else {
                    Toast.makeText(AccountSettingsActivity.this, "Account updated successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
