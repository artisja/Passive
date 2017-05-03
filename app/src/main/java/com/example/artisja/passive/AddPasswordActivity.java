package com.example.artisja.passive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPasswordActivity extends AppCompatActivity {

    EditText newSiteEditText,newPasswordEditText;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        setUpViews();
        setUpOnClickListeners();
    }

    private void setUpOnClickListeners() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!newSiteEditText.getText().toString().isEmpty() && !newPasswordEditText.getText().toString().isEmpty()) {
                    Home.passwordSet.add(new Login(newSiteEditText.getText().toString(), newPasswordEditText.getText().toString()));
                    Intent toHome = new Intent(AddPasswordActivity.this,Home.class);
                    startActivity(toHome);
                }else {
                    Toast.makeText(AddPasswordActivity.this, "Empty Inputs", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setUpViews() {
        newSiteEditText = (EditText) findViewById(R.id.new_site_edit);
        newPasswordEditText = (EditText) findViewById(R.id.new_password_edit);
        submitButton = (Button) findViewById(R.id.submit_new_password);
    }
}
