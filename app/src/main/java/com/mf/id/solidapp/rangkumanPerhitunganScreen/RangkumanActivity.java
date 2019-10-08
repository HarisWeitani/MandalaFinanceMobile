package com.mf.id.solidapp.rangkumanPerhitunganScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mf.aplikasisolid.syaratScreen.PersyaratanActivity;
import com.mf.id.solidapp.R;

import org.apache.poi.ss.formula.functions.FinanceLib;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RangkumanActivity extends AppCompatActivity {

    Toolbar tb;
    Button syarat;
    TextView pokokHutang, sukuBunga, tenor, angsuran , catatan, rincian;

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

        pokokHutang = findViewById(R.id.pokokHutangTV);
        sukuBunga = findViewById(R.id.sukuBungaTV);
        tenor = findViewById(R.id.tenorTV);
        angsuran = findViewById(R.id.angsuranPerBulanTV);
        catatan = findViewById(R.id.detailCatatanTV);
        rincian = findViewById(R.id.detailRincianTV);

        pokokHutang.setText("Rp. 400.000.000");
        sukuBunga.setText("0.95%");
        tenor.setText("1 tahun");

        Long angsuranV = Math.round(FinanceLib.pmt(0.2050/12,12,-400000000,0,false)/100)*100;

        angsuran.setText("Rp. " + NumberFormat.getNumberInstance(new Locale("in","ID")).format(angsuranV));

        catatan.setText(getString(R.string.catatan) + "\n" + new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date()) );
        rincian.setText(R.string.rincian);

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
