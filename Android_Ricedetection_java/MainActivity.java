package com.example.predictiondetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.login);
        Button button2 = (Button) findViewById(R.id.sign_Up);
        Button guest =(Button) findViewById(R.id.guest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSign();
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencrop();
            }
        });
    }

    private void openlogin() {
        Intent intent = new Intent( this, login.class);
        startActivity(intent);
    }
    public void openSign()
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public  void  opencrop()
    {
        Intent intent = new Intent( this, crop.class);
        startActivity(intent);
    }
}