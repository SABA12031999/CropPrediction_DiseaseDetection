package com.example.predictiondetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo =(ImageView) findViewById(R.id.logo);


        Animation anim = AnimationUtils.loadAnimation(this,R.anim.transaction);
        logo.setAnimation(anim);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}