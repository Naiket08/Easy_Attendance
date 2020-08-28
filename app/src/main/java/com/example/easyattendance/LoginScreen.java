package com.example.easyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    TextView textViewAlreadyAccount, textViewNewUser, textViewSignUp;
    EditText editTextEmail, editTextPassword;
    ImageView imageViewPerson;
    Button buttonLogin;

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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextEmail.getText().toString().equals("patelnaiket08@gmail.com")&&editTextPassword.getText().toString().equals("12345678")){
                    Toast.makeText(LoginScreen.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                }
                else{
                    editTextPassword.setError("Wrong Password");
                }
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginScreen.this, "Work under progress", Toast.LENGTH_SHORT).show();
            }
        });
    }
}