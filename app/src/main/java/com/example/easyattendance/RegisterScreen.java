package com.example.easyattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.opencensus.tags.Tag;

public class RegisterScreen extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView textViewHaveAccount, textViewLogin, textViewWelcometoeasyattendance;
    EditText editTextRegisterName, editTextRegisterMobile, editTextRegisterEmail, editTextRegisterPassword, editTextBranch;
    Button buttonRegister;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_screen);

        textViewWelcometoeasyattendance = (TextView)findViewById(R.id.textViewWelcometoeasyattendance);
        textViewHaveAccount = (TextView)findViewById(R.id.textViewHaveAccount);
        textViewLogin = (TextView)findViewById(R.id.textViewLogin);
        editTextRegisterName = (EditText)findViewById(R.id.editTextRegisterName);
        editTextRegisterMobile = (EditText)findViewById(R.id.editTextRegisterMobile);
        editTextBranch = (EditText)findViewById(R.id.editTextBranch);
        editTextRegisterEmail = (EditText)findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = (EditText)findViewById(R.id.editTextRegisterPassword);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextRegisterEmail.getText().toString().trim();
                String pwd = editTextRegisterPassword.getText().toString().trim();
                final String name = editTextRegisterName.getText().toString();
                final String mobileno = editTextRegisterMobile.getText().toString();
                final String branch = editTextBranch.getText().toString();

                if(TextUtils.isEmpty(email)){
                    editTextRegisterEmail.setError("Email ID is required");
                    return;
                }

                if(TextUtils.isEmpty(pwd)){
                    editTextRegisterPassword.setError("Password is required");
                    return;
                }

                if(pwd.length() < 6){
                    editTextRegisterPassword.setError("Length of password must be >= 6 characters");
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterScreen.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",name);
                            user.put("Mobile No.",mobileno);
                            user.put("Branch", branch);
                            user.put("Email ID",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess : user profile is created for "+ userID );
                                }
                            });
                            startActivity(new Intent(RegisterScreen.this,MainScreen.class));
                        }
                        else {
                            Toast.makeText(RegisterScreen.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });


    }
}