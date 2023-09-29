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

public class Register1 extends AppCompatActivity {

    EditText username, email, password;

    Button buttonsignup;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    TextView textViewLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        username = findViewById(R.id.username);
       email =findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonsignup = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.progressbar);
        textViewLogin = findViewById(R.id.Login);
        mAuth= FirebaseAuth.getInstance();

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login1.class);
                startActivity(intent);
                finish();
            }
        });
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = username.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();


                if (userName.isEmpty()) {
                    username.setError("Lastname is required");
                    username.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(userEmail)) {
                  email.setError("Email is required");
                    email.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    email.setError("Please enter valid Email Address");
                    email.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(userPassword)) {
                    password.setError("Password is required");
                    password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (password.length()<6) {
                    password.setError("Minimum password length is 6 characters");
                    password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register1.this, "Login Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login1.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(Register1.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }


    public void LOGIN(View view) {
        startActivity(new Intent(Register1.this, Login1.class));
    }
}