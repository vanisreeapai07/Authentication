package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
     EditText lemail,lpassword;
     Button btn,btn2;
     FirebaseAuth Fauth;
     TextView fgp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lemail= findViewById(R.id.email);
        lpassword = findViewById(R.id.password);
        fgp = findViewById(R.id.forgot);
        btn = findViewById(R.id.login);
        Fauth = FirebaseAuth.getInstance();

        btn2 = findViewById(R.id.btnSignInT);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),signUp.class);
                startActivity(intent);

            }
        });
        fgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),forgotpassword.class);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();

                Fauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),phoneno.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        }
    }
