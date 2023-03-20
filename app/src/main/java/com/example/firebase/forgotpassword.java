package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    EditText forgotpassword;
    Button fpsubmit;
    FirebaseAuth Fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpassword = findViewById(R.id.forgotpassword);
        Fauth = FirebaseAuth.getInstance();

        fpsubmit = findViewById(R.id.fpsubmit);

        fpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = forgotpassword.getText().toString().trim();
                Fauth.sendPasswordResetEmail(s1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(forgotpassword.this, "password changed", Toast.LENGTH_SHORT).show();
                    }
                }).


                        addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(forgotpassword.this, "failed text", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);



            }
        });
    }
}