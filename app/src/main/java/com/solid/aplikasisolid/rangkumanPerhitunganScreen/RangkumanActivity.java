package com.solid.aplikasisolid.rangkumanPerhitunganScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.solid.aplikasisolid.R;
import com.solid.aplikasisolid.syaratScreen.PersyaratanActivity;

public class RangkumanActivity extends AppCompatActivity {

    Toolbar tb;
    Button syarat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rangkuman_perhitungan);
        tb = findViewById(R.id.rangkumanTB);
        setSupportActionBar(tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        syarat = findViewById(R.id.persyaratanB);
        syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), PersyaratanActivity.class);
                startActivity(i);
            }
        });
    }
}
