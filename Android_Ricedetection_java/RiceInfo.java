package com.example.predictiondetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RiceInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_info);
        Button BSpot= (Button)findViewById(R.id.brown_spot_info);
        Button BLB=(Button)findViewById(R.id.bacterial_leaf_blight_info);
        Button Leafsmuf=(Button)findViewById(R.id.leaf_smut_info);
        Leafsmuf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opensmuf();
            }
        });



        BLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenBLB();
            }
        });
        BSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenBSPOT();
            }
        });

    }
    public void Opensmuf()
    {
        Intent intent=new Intent(this,leaf_smut.class);
        startActivity(intent);
    }

    public void OpenBLB()
    {
       Intent intent= new Intent(this,bacterial_leaf_blight.class);
       startActivity(intent);
    }
    public void OpenBSPOT()
    {
        Intent intent=new Intent(this,brown_spot.class);
        startActivity(intent);
    }


}
