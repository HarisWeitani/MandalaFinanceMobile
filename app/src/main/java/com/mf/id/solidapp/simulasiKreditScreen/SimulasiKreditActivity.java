package com.mf.id.solidapp.simulasiKreditScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.simulasiKreditScreen.adapter.SimulasiAdapter;
import com.mf.id.solidapp.simulasiKreditScreen.model.SimulasiModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimulasiKreditActivity extends AppCompatActivity {

    Toolbar tb;
    RecyclerView simulasiRV;
    SimulasiAdapter adapter;
    List<SimulasiModel> modelList = new ArrayList<>();
    TextView catatan, rincian;
    String pokok, jenis, tahun;
    int usia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_simulasi_kredit);
        tb = findViewById(R.id.simulasiTB);
        simulasiRV = findViewById(R.id.simulasiKreditRV);
        catatan = findViewById(R.id.simulasiCatatanTV);
        rincian = findViewById(R.id.simulasiRincianTV);
        setSupportActionBar(tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //need some validation for getting data

        Intent i = getIntent();
        pokok = i.getStringExtra("pokok");
        jenis = i.getStringExtra("jenis");
        tahun = i.getStringExtra("tahun");

        usia = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(tahun);
        Toast.makeText(getBaseContext(),String.valueOf(usia),Toast.LENGTH_LONG).show();

        modelList.add(new SimulasiModel(12,20.5));
        modelList.add(new SimulasiModel(24,21));
        modelList.add(new SimulasiModel(36,21.5));

        catatan.setText(getString(R.string.catatan) + "\n" + new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date()) );
        rincian.setText(R.string.rincian);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Long tempPokok = Long.parseLong(pokok.replace("Rp. ","").replaceAll("[.]",""));
        adapter = new SimulasiAdapter(getBaseContext(),modelList,tempPokok);
        simulasiRV.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        simulasiRV.setAdapter(adapter);

    }
}
