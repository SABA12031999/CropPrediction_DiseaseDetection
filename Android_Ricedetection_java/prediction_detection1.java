package com.example.predictiondetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class prediction_detection1 extends AppCompatActivity implements View.OnClickListener {
    Button prediction1,detection1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_detection1);
        getSupportActionBar().setTitle("Prediction Detection 1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        prediction1 = (Button) findViewById(R.id.prediction1);
        detection1 = (Button) findViewById(R.id.detection1);

        prediction1.setOnClickListener(this);

        detection1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == prediction1){
            Intent intent = new Intent( this, prediction1.class);
            startActivity(intent);

        }else if (v == detection1){
            Intent intent = new Intent( this, detection1.class);
            startActivity(intent);

        }

    }
}