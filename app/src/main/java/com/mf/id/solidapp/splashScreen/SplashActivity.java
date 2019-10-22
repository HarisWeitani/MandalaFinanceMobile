package com.mf.id.solidapp.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.homeScreen.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        pb = findViewById(R.id.splashPB);
        pb.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.VISIBLE);

            }
        },2000);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },getResources().getInteger(R.integer.splash_time));
    }
}
