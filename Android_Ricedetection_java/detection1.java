package com.example.predictiondetection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detection1 extends AppCompatActivity {

    private ImageView mAvatarImage;
private Button detectBtn;
private TextView results;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection1);
        Button rinfo = (Button) findViewById(R.id.riinfo);
        rinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRinfo();
            }
        });


        mAvatarImage = (ImageView) findViewById(R.id.imageView);
results=(TextView)findViewById(R.id.results);
progressBar=(ProgressBar)findViewById(R.id.indeterminateBar);
progressBar.setVisibility(View.INVISIBLE);
        results.setText("");
        detectBtn = (Button) findViewById(R.id.open);
        if (ContextCompat.checkSelfPermission(detection1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(detection1.this, new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.setText("");
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        // do anything before post data.. or triggered after button clicked

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            mAvatarImage.setImageBitmap(captureImage);
            saveProfileAccount();
        }
    }
    private void saveProfileAccount() {
        // loading or check internet connection or something...
        // ... then
        progressBar.setVisibility(View.VISIBLE);
        String url = "http://10.0.2.2:5000/";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONObject result = new JSONObject(resultResponse);
                    Integer status = result.getInt("status");
                    String message = result.getString("classes");
                    Log.i("Status", status.toString());
                    if (status==200) {
                        // tell everybody you have succed upload image and post strings
                        Log.i("Messsage", message);
                        results.setText(message);
                    } else {
                        Log.i("Unexpected", message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("api_token", "gh659gjhvdyudo973823tt9gvjf7i6ric75r76");

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("image", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
                return params;
            }
        };

        VolleySingelon.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
    }

    public void OpenRinfo()
    {
        Intent intent = new Intent(this, RiceInfo.class);
        startActivity(intent);
    }
}