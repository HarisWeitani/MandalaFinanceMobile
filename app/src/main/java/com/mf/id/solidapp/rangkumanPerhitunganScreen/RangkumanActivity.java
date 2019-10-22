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

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.syaratScreen.PersyaratanActivity;

import org.apache.poi.ss.formula.functions.FinanceLib;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RangkumanActivity extends AppCompatActivity {

    Toolbar tb;
    Button syarat;
    TextView pokokHutang, sukuBunga, tenor, angsuran , catatan, rincian;
    Long pokok, angsuranV;
    int durasi;
    double bunga;

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

        Intent x = getIntent();
        pokok = x.getLongExtra("pokok",0);
        angsuranV = x.getLongExtra("angsuran",0);
        durasi = x.getIntExtra("durasi",0);
        bunga = x.getDoubleExtra("bunga",0);


        long ar = angsuranV*durasi;
        long totalBunga = ar-pokok;
        float bungaFlat = Math.round(totalBunga*10000/pokok/(durasi/12));
        float sukuBungaV = Math.round(bungaFlat/12);
        pokokHutang.setText("Rp. " + NumberFormat.getNumberInstance(new Locale("in","ID")).format(pokok));
        sukuBunga.setText(Float.valueOf(sukuBungaV/100) + " %" );
        tenor.setText(durasi + " Bulan");
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
