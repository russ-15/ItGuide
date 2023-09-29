package com.example.itguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        LinearLayout logout = findViewById(R.id.setting5);
        LinearLayout helpcenter = findViewById(R.id.setting4);
        LinearLayout security = findViewById(R.id.setting3);

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}