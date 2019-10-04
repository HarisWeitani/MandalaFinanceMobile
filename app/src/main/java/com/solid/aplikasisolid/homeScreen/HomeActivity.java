package com.solid.aplikasisolid.homeScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.solid.aplikasisolid.R;
import com.solid.aplikasisolid.simulasiKreditScreen.SimulasiKreditActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Toolbar tb;
    EditText pokok;
    Spinner jenis,tahun;
    Button hitung;

    List<String> listJenis = new ArrayList<>();
    List<Integer> listTahun = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        tb = findViewById(R.id.homeToolbar);
        pokok = findViewById(R.id.pokokET);
        jenis = findViewById(R.id.jenisSpinner);
        tahun = findViewById(R.id.tahunSpinner);
        hitung = findViewById(R.id.hitungB);
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listJenis = Arrays.asList(getResources().getStringArray(R.array.jenis));
        ArrayAdapter jenisAdapter = new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,listJenis);
        jenis.setAdapter(jenisAdapter);
        for(int x = 2018; x>=2005;x--){
            listTahun.add(x);
        }
        ArrayAdapter tahunAdapter = new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,listTahun);
        tahun.setAdapter(tahunAdapter);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SimulasiKreditActivity.class);
                startActivity(i);
            }
        });
    }
}
