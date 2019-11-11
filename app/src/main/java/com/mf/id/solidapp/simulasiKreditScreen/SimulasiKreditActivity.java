package com.mf.id.solidapp.simulasiKreditScreen;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.mf.id.solidapp.Util.ResponseDataModel;
import com.mf.id.solidapp.Util.database.AppDatabase;
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
    TextView catatan, rincian, peringatan;
    String pokok, jenis, tahun;
    int usia, maxBeda;

    AppDatabase db;
    List<ResponseDataModel> listData = new ArrayList<>();

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
        peringatan = findViewById(R.id.peringatanTV);
        peringatan.setVisibility(View.GONE);

        setSupportActionBar(tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        db = AppDatabase.getInstance(getApplicationContext());

        Intent i = getIntent();
        pokok = i.getStringExtra("pokok");
        jenis = i.getStringExtra("jenis");
        tahun = i.getStringExtra("tahun");
        maxBeda = i.getIntExtra("maxBeda", 0);

        usia = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(tahun);
        final String filterUsia;
        if (usia < 5) {
            filterUsia = "%< 5%";
        } else if (usia < 10) {
            filterUsia = "%5 - 10%";
        } else {
            filterUsia = "%11 - 15%";
        }

        final Long tempPokok = Long.parseLong(pokok.replace("Rp. ", "").replaceAll("[.]", ""));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                listData = db.userDao().getFilteredData(filterUsia, jenis);
                for (ResponseDataModel model : listData) {
                    int tenor = Integer.parseInt(model.getDt_interest_name().replace(" Bulan", ""));
                    double bunga = Double.parseDouble(model.getDt_interest_value());
                    if ((usia + (tenor / 12)) <= maxBeda) {
                        modelList.add(new SimulasiModel(tenor, bunga));
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new SimulasiAdapter(getBaseContext(), modelList, tempPokok);
                        simulasiRV.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                        simulasiRV.setAdapter(adapter);

                        if(modelList.isEmpty() && maxBeda <15){
                            peringatan.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        catatan.setText(getString(R.string.catatan) + "\n" + new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date()));
        rincian.setText(R.string.rincian);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
