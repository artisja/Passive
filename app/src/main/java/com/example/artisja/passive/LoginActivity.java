package com.example.artisja.passive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText loginEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpWidgets();
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setUpWidgets(){
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
    }
}
