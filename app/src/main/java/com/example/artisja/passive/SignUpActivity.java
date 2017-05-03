package com.example.artisja.passive;

import android.*;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;

public class SignUpActivity extends AppCompatActivity {


    TextView promptText;
    private AlertDialog dialog;
    EditText dialogEdit;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference();
    private PendingIntent sendPI;
    boolean isCanceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Context context = this;
        sendPI =  PendingIntent.getActivity(context,0,new Intent(context,SignUpActivity.class),0);
        setUpViews(context);
        dialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isCanceled){
                    dialog.cancel();
                }
            }
        },10000);

    }

    private void setUpViews(Context context) {
        promptText = (TextView) findViewById(R.id.prompt);
        dialogEdit = new EditText(SignUpActivity.this);
        dialogEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialogEdit.setLayoutParams(layoutParams);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setIcon(R.drawable.keyicon)
                .setMessage(R.string.sign_up_alert_message)
                .setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (dialogEdit.getText().toString().isEmpty()){
                            Toast.makeText(SignUpActivity.this, "Empty or Not Number", Toast.LENGTH_SHORT).show();

                        }else{
                            String input = dialogEdit.getText().toString();
                            ref.setValue(input);
                            ref = ref.child(input);
                            SecureRandom secureRandom = new SecureRandom();
                            byte[] values = new byte[20];
                            secureRandom.nextBytes(values);
                            Integer hashPassword =  secureRandom.nextInt();
                            if (hashPassword<0){
                                hashPassword = hashPassword *-1;
                            }
                            ref.setValue(hashPassword);
                            setUpSMS();
                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(input,null,hashPassword.toString(),sendPI,null);
                        }
                        isCanceled = true;
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.setView(dialogEdit);
    }

    private void setUpSMS() {
        ActivityCompat.requestPermissions(SignUpActivity.this,
                new String[]{android.Manifest.permission.SEND_SMS}, 0);
    }
}
