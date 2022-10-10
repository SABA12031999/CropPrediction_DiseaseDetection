package com.example.predictiondetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class
crop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        getSupportActionBar().setTitle("Crop");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Button wheat = (Button) findViewById(R.id.wheat);
        Button rice = (Button) findViewById(R.id.rice);

//        wheat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                openwheat();
//            }
//        });

        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openrice();
            }
        });
    }
//    public void openwheat()
//    {
//        Intent intent = new Intent(this, prediction_detection2.class);
//                startActivity(intent);
//    }
    public void openrice()
    {
        Intent intent = new Intent(this, prediction_detection1.class);
        startActivity(intent);
    }
}