package com.example.artisja.passive;

import android.*;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
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

import java.security.SecureRandom;

import static android.R.id.content;


public class LoginActivity extends AppCompatActivity {

    EditText loginEditText;
    Button loginButton,signUpButton;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference("8045869402");
    String data;
    static Integer hashPassword;
    private PendingIntent sendPI;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        sendPI =  PendingIntent.getActivity(context,0,new Intent(context,Home.class),0);
        setUpWidgets();
        setUpSMS();
        setUpClickListeners();

    }

    private void setUpSMS() {
        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{Manifest.permission.SEND_SMS}, 0);
    }

    private void setUpClickListeners() {
        final LoginActivity loginActivity = this;
        loginEditText.setHint("Type in Number Code");
        loginEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicked", "Login Clicked");
                Log.d("ref check", ref.toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("dataSnapshot", dataSnapshot.getValue().toString());
                        data = dataSnapshot.getValue().toString();
        if (data.equals(loginEditText.getText().toString())){
            Log.d("login Check: ","Its good.");
            Toast.makeText(LoginActivity.this, "Valid PassCode", Toast.LENGTH_SHORT).show();
            SecureRandom secureRandom = new SecureRandom();
            byte[] values = new byte[20];
            secureRandom.nextBytes(values);
            hashPassword =  secureRandom.nextInt();
            if (hashPassword<0){
                hashPassword = hashPassword *-1;
            }
            ref.setValue(hashPassword);
            sendPI =  PendingIntent.getActivity(context,0,new Intent(context,Home.class),0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("8045869402",null,LoginActivity.hashPassword.toString(),sendPI,null);
//            Intent toHome = new Intent(loginActivity,Home.class);
//            toHome.putExtra("Reference",ref.toString());
//            startActivity(toHome);
        }else{
            Log.d("Wrong: ","Incorrect input");
            }
        }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(loginActivity, "Check your Wifi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setUpWidgets(){
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
    }
}
