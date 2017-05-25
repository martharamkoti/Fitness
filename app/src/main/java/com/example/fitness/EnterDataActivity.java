package com.example.fitness;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterDataActivity extends AppCompatActivity {

    Button saveData,cancelActivity;
    EditText weightEditText,workOutTimeEditText,stepsEditText,distanceEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        saveData = (Button) findViewById(R.id.saveData);
        cancelActivity = (Button) findViewById(R.id.cancelEnterPage);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        workOutTimeEditText = (EditText) findViewById(R.id.workOutTimeEditText);
        stepsEditText = (EditText) findViewById(R.id.stepsEditText);
        distanceEditText = (EditText) findViewById(R.id.distanceEditText);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightEditText.setError(null);
                workOutTimeEditText.setError(null);
                stepsEditText.setError(null);
                distanceEditText.setError(null);
                if (weightEditText.getText().toString().length() == 0) {
                    weightEditText.setError("Required");
                    weightEditText.requestFocus();
                } else if (workOutTimeEditText.getText().toString().length() == 0) {
                    workOutTimeEditText.setError("Required");
                    workOutTimeEditText.requestFocus();
                } else if (stepsEditText.getText().toString().length() == 0) {
                    stepsEditText.setError("Required");
                    stepsEditText.requestFocus();
                } else if (distanceEditText.getText().toString().length() == 0) {
                    distanceEditText.setError("Required");
                    distanceEditText.requestFocus();
                } else {
                    Toast.makeText(EnterDataActivity.this,"All filds are OK",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelActivity.setOnClickListener(new View.OnClickListener() {
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
