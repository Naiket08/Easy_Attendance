package com.example.easyattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    TextView textViewAlreadyAccount, textViewNewUser, textViewSignUp;
    EditText editTextEmail, editTextPassword;
    ImageView imageViewPerson;
    Button buttonLogin;
    ProgressBar progressBarLoginScreen;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        textViewAlreadyAccount = (TextView)findViewById(R.id.textViewAlreadyAccount);
        textViewNewUser = (TextView)findViewById(R.id.textViewNewUser);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        imageViewPerson = (ImageView)findViewById(R.id.imageViewPerson);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        progressBarLoginScreen = (ProgressBar)findViewById(R.id.progressBarLoginScreen);

        //FirebaseAuth initialization
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginScreen.this,MainScreen.class));
            finish();
        }

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String pwd = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email ID is required");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    editTextPassword.setError("Password is required");
                    return;
                }

                if (pwd.length() < 6) {
                    editTextPassword.setError("Length of password must be >= 6 characters");
                }

                progressBarLoginScreen.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginScreen.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginScreen.this, MainScreen.class));
                        }
                        else{
                            Toast.makeText(LoginScreen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarLoginScreen.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

    }
}