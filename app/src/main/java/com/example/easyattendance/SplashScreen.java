package com.example.easyattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView textViewEasy, textViewAttendance, textViewSlogan;
    ImageView imageViewRectangleOne, imageViewRectangleTwo, imageViewRectangleThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        textViewEasy = (TextView)findViewById(R.id.textViewEasy);
        textViewAttendance = (TextView)findViewById(R.id.textViewAttendance);
        textViewSlogan = (TextView)findViewById(R.id.textViewSlogan);

        imageViewRectangleOne = (ImageView)findViewById(R.id.imageViewRectangleOne);
        imageViewRectangleTwo = (ImageView)findViewById(R.id.imageViewRectangleTwo);
        imageViewRectangleThree = (ImageView)findViewById(R.id.imageViewRectangleThree);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}