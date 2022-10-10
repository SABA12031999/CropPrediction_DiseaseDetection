package com.example.predictiondetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    TextView alreadylogin;
    EditText memail, mpassword, mphone;
    Button signup;


    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        memail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        mpassword = (EditText) findViewById(R.id.Password2);
        mphone = (EditText) findViewById(R.id.phone);
        alreadylogin = (TextView) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        alreadylogin.setOnClickListener(this);
        signup.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if (v == alreadylogin) {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);

        } else if (v == signup) {

            Sign_Up();

        }


    }

    private void Sign_Up() {
        String email = memail.getText().toString().trim();
        String password =mpassword.getText().toString().trim();
        String phone = mphone.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            memail.setError("Email is Required.");
            memail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mpassword.setError("Password is Required.");
            mpassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            mphone.setError("Phone is Required.");
            mphone.requestFocus();
            return;
        }

        if (password.length() < 6) {
            mpassword.setError("Password Must be >= 6 Characters");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            memail.setError("Please provide valid email");
            return;
        }

        Intent crop = new Intent(SignUp.this,crop.class);
        startActivity(crop);

        progressBar.setVisibility(View.VISIBLE);

//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            User user = new User(email, phone);
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//
//                                    if(task.isSuccessful()) {
//                                        Toast.makeText(SignUp.this, "User has been sign in successfully! ", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        Toast.makeText(SignUp.this, "Failed sign-in! Try again! ", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//
//                                }
//                            });
//                        }else{
//                            Toast.makeText(SignUp.this,"Failed sign-in! Try again! ",Toast.LENGTH_LONG).show();
//                            progressBar.setVisibility(View.GONE);
//
//                        }
//
//                    }
//                });

    }


}