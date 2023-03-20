package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phoneno extends AppCompatActivity {
    EditText phno,otp;
    Button sotp,ver;
    private String verification;
    FirebaseAuth fauth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneno);

       phno = findViewById(R.id.phno);
       otp = findViewById(R.id.otp);
       sotp = findViewById(R.id.sotp);
       ver = findViewById(R.id.ver);
      fauth = FirebaseAuth.getInstance();
      sotp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (!phno.getText().toString().isEmpty() && phno.getText().toString().length()==10) {
                  String phoneNum = "+91" + phno.getText().toString();
                  requestOTP(phoneNum);
              } else {
                  phno.setError("phone number is not valid");
              }
          }
      });
      ver.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              verifyCode(otp.getText().toString());
          }
      });



    }
    private void requestOTP(String phoneNum){

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(fauth)
                .setPhoneNumber(phoneNum)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(phoneno.this)
                .setCallbacks(mCallbacks)
                .build();


        PhoneAuthProvider.verifyPhoneNumber(options); //verifying the ph no.


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verification = s;
        }

        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                otp.setText(code);
            }
            verifyCode(code);
        }



        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(phoneno.this, "verification failed", Toast.LENGTH_SHORT).show();

        }
    };
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        fauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(phoneno.this,MainActivity2.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(phoneno.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

