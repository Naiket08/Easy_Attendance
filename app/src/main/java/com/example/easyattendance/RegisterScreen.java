package com.example.easyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    ImageView imageViewRegisterPerson;
    EditText editTextRegisterName, editTextRegisterMobile, editTextRegisterEmail, editTextRegisterPassword;
    Button buttonRegister;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_screen);

        //firebaseauth initialization
        firebaseAuth = FirebaseAuth.getInstance();

        imageViewRegisterPerson = (ImageView)findViewById(R.id.imageViewRegisterPerson);
        editTextRegisterName = (EditText)findViewById(R.id.editTextRegisterName);
        editTextRegisterMobile = (EditText)findViewById(R.id.editTextRegisterMobile);
        editTextRegisterEmail = (EditText)findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = (EditText)findViewById(R.id.editTextRegisterPassword);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);


    }
}