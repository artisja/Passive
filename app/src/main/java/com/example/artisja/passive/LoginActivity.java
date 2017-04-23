package com.example.artisja.passive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.android.*;
import org.json.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.*;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.id.content;


public class LoginActivity extends AppCompatActivity {

    EditText loginEditText;
    Button loginButton;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference("Code");
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpWidgets();
        setUpClickListeners();
        setUpSMS();
    }

    private void setUpSMS() {
          SharedPreferences settings = getSharedPreferences("UserInfo", 0);

//        params.put("api_key", settings.getString("api_key", ""));
//        params.put("api_secret", settings.getString("api_secret",""));
//        params.put("from", originator.getText().toString());
//        params.put("to", destination.getText().toString());
//        params.put("text", content.getText().toString());

    }

    private void setUpClickListeners() {
        final LoginActivity loginActivity = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicked","Login Clicked");

                Log.d("ref check",ref.toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("dataSnapshot", dataSnapshot.getValue().toString());
                        data = dataSnapshot.getValue().toString();
        if (data.equals(loginEditText.getText().toString())){
            Log.d("login Check: ","Its good.");
            Toast.makeText(LoginActivity.this, "Valid PassCode", Toast.LENGTH_SHORT).show();
            Intent toHome = new Intent(loginActivity,Home.class);
            startActivity(toHome);
        }else{
            Log.d("Wrong: ","Incorrect input");
            Toast.makeText(LoginActivity.this, "Incorrect or Empty Passcode", Toast.LENGTH_SHORT).show();
            }
        }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(loginActivity, "Check your Wifi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void setUpWidgets(){
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
    }
}
