package com.mf.id.solidapp.simulasiKreditScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mf.aplikasisolid.simulasiKreditScreen.adapter.SimulasiAdapter;
import com.mf.aplikasisolid.simulasiKreditScreen.model.SimulasiModel;
import com.mf.id.solidapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimulasiKreditActivity extends AppCompatActivity {

    Toolbar tb;
    RecyclerView simulasiRV;
    SimulasiAdapter adapter;
    List<SimulasiModel> modelList = new ArrayList<>();
    TextView catatan, rincian;

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
        modelList.add(new SimulasiModel("1 tahun","Rp. 1.000.000"));
        modelList.add(new SimulasiModel("2 tahun","Rp. 2.000.000"));
        modelList.add(new SimulasiModel("3 tahun","Rp. 3.000.000"));

        catatan.setText(getString(R.string.catatan) + "\n" + new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date()) );
        rincian.setText(R.string.rincian);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new SimulasiAdapter(getBaseContext(),modelList);
        simulasiRV.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        simulasiRV.setAdapter(adapter);

    }
}
