package com.solid.aplikasisolid.simulasiKreditScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.solid.aplikasisolid.R;
import com.solid.aplikasisolid.simulasiKreditScreen.adapter.SimulasiAdapter;
import com.solid.aplikasisolid.simulasiKreditScreen.model.SimulasiModel;

import java.util.ArrayList;
import java.util.List;

public class SimulasiKreditActivity extends AppCompatActivity {

    Toolbar tb;
    RecyclerView simulasiRV;
    SimulasiAdapter adapter;
    List<SimulasiModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_simulasi_kredit);
        tb = findViewById(R.id.simulasiTB);
        simulasiRV = findViewById(R.id.simulasiKreditRV);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new SimulasiAdapter(getBaseContext(),modelList);
        simulasiRV.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        simulasiRV.setAdapter(adapter);

    }
}
