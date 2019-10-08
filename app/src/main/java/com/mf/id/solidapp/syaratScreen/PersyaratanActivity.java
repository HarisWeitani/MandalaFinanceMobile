package com.mf.id.solidapp.syaratScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mf.aplikasisolid.syaratScreen.adapter.PersyaratanAdapter;
import com.mf.id.solidapp.R;

import java.util.Arrays;
import java.util.List;

public class PersyaratanActivity extends AppCompatActivity {

    Toolbar tb;
    RecyclerView syaratUmum, syaratKendaraan;
    List<String> listUmum,listKendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_persyaratan);
        tb = findViewById(R.id.syaratTB);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        syaratUmum = findViewById(R.id.syaratUmumRV);

        listUmum = Arrays.asList(getResources().getStringArray(R.array.syarat_umum));
        PersyaratanAdapter adapterUmum = new PersyaratanAdapter(getBaseContext(),listUmum);
        syaratUmum.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        syaratUmum.setAdapter(adapterUmum);

        syaratKendaraan = findViewById(R.id.syaratKendaraanRV);

        listKendaraan = Arrays.asList(getResources().getStringArray(R.array.syarat_kendaraan));
        PersyaratanAdapter adapterKendaraan = new PersyaratanAdapter(getBaseContext(),listKendaraan);
        syaratKendaraan.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        syaratKendaraan.setAdapter(adapterKendaraan);

    }
}
