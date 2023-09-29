package com.example.itguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login1 extends AppCompatActivity {
    EditText email, password;
    Button buttonsignin;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    TextView textViewRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonsignin = findViewById(R.id.btn_signin);
        progressBar = findViewById(R.id.progressbar);
        textViewRegister = findViewById(R.id.Register);
        mAuth= FirebaseAuth.getInstance();

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register1.class);
                startActivity(intent);
                finish();

            }
        });

        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();


                if (TextUtils.isEmpty(userEmail)) {
                    email.setError("Email is required");
                    email.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher (userEmail).matches()) {
                  email.setError("Please enter valid Email Address");
                  email.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if(TextUtils.isEmpty(userPassword)){
                    password.setError("Password is required");
                    password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if(password.length()<6) {
                    password.setError("Minimum password length is 6 characters");
                    password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Login1.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Homepage.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(Login1.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    public void Create(View view) {
        startActivity(new Intent(Login1.this,Register1.class));
    }
}