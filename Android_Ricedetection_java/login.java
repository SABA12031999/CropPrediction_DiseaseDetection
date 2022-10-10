package com.example.predictiondetection;

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

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email,password;
    Button sign_in;
    TextView sign_up;

    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.EmailAddress2);
        password=(EditText) findViewById(R.id.Password1);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_up =(TextView) findViewById(R.id.signup);

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v == sign_in){
            Sign_in();
        }
        else if (v == sign_up){
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);

        }

    }

    private void Sign_in() {
        String Email = email.getText().toString().trim();
        String Password =password.getText().toString().trim();



        if (TextUtils.isEmpty(Email)) {
            email.setError("Email is Required.");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            password.setError("Password is Required.");
            password.requestFocus();
            return;
        }


        if (password.length() < 6) {
            password.setError("Password Must be >= 6 Characters");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please provide valid email");
            return;
        }

        Intent crop = new Intent(login.this,crop.class);
        startActivity(crop);

        progressBar.setVisibility(View.VISIBLE);


    }
}
