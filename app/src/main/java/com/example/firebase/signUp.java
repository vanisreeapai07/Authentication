package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUp extends AppCompatActivity {
    EditText rname,remail,rpassword,rphone;
    Button register;
    TextView mLogin;
    FirebaseAuth Fauth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        rname = findViewById(R.id.rname);


        remail = findViewById(R.id.remail);
        String etname = rname.getText().toString();
        rpassword =  findViewById(R.id.rpassword);
        rphone = findViewById(R.id.rphone);
        String etphone = rphone.getText().toString().trim();
        register = findViewById(R.id.register);
        Fauth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = remail.getText().toString().trim();
                String password = rpassword.getText().toString().trim();

                Intent intent = new Intent(signUp.this, MainActivity2.class);
                startActivity(intent);
             Fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         FirebaseUser fuser = Fauth.getCurrentUser();
                         fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {
                                 Toast.makeText(signUp.this, "registeration sucessfull", Toast.LENGTH_SHORT).show();

                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(signUp.this, "registeration failer", Toast.LENGTH_SHORT).show();
                             }
                         });
                     }
                 }
             });
            }

        });

    }
}

