package com.mf.id.solidapp.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.simulasiKreditScreen.SimulasiKreditActivity;
import com.mf.id.solidapp.splashScreen.SplashActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    Toolbar tb;
    EditText pokok;
    Spinner jenis,tahun;
    Button hitung;
    String prefix;
    double pokokV ;

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

        prefix = "Rp. ";

        pokok.setText(prefix + 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logo){
            Intent i = new Intent(getBaseContext(), SplashActivity.class);
            startActivity(i);
            finish();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        pokok.setSelection(pokok.getText().length());
        pokok.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private int index;
            private boolean deletingDecimalPoint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(after>0){
                    index = s.length()-start;
                }else{
                    index = s.length()-start-1;
                }
                if(count > 0 && s.charAt(start) == '.'){
                    deletingDecimalPoint = true;
                }else{
                    deletingDecimalPoint = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public synchronized void afterTextChanged(Editable s) {
                if(!s.toString().equals(current)){
                    pokok.removeTextChangedListener(this);
                    if(deletingDecimalPoint)
                        s.delete(s.length()-index-1,s.length()-index);

                    String temp = s.toString();
                    //set non edited value
                    if(!temp.startsWith(prefix)){
                        temp = current;
                    }
                    String v_text = temp.replace(prefix,"").replaceAll("[.]","");
                    double v_value = 0;
                    if(v_text != null && v_text.length()>0) {
                        v_value = Double.parseDouble(v_text);
                        pokokV = v_value;
                    }
                    //set edited value
                    String v_formated = prefix + NumberFormat.getNumberInstance(new Locale("in","ID")).format(v_value);
                    current = v_formated;

                    pokok.setText(v_formated);
                    if(index > v_formated.length() || v_formated.length() - index < prefix.length()){
                        pokok.setSelection(v_formated.length());
                    }else{
                        pokok.setSelection(v_formated.length() - index);
                    }

                    pokok.addTextChangedListener(this);
                }
            }
        });

        listJenis = Arrays.asList(getResources().getStringArray(R.array.jenis));
        ArrayAdapter jenisAdapter = new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,listJenis);
        jenis.setAdapter(jenisAdapter);

        listTahun = new ArrayList<>();
        for(int x = 2018; x>=2005;x--){
            listTahun.add(x);
        }
        ArrayAdapter tahunAdapter = new ArrayAdapter(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,listTahun);
        tahun.setAdapter(tahunAdapter);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokokV<50000000){
                    pokok.setError("Pokok pinjaman harus diatas 50 juta");
                }else if(pokokV>200000000){
                    pokok.setError("Pokok pinjaman harus dibawah 200 juta");
                }else{
                    String pokokPinjaman = pokok.getText().toString();
                    String selectedJenis = jenis.getSelectedItem().toString();
                    String selectedTahun = tahun.getSelectedItem().toString();

                    Intent i = new Intent(getBaseContext(), SimulasiKreditActivity.class);
                    i.putExtra("jenis",selectedJenis);
                    i.putExtra("pokok",pokokPinjaman);
                    i.putExtra("tahun",selectedTahun);
                    startActivity(i);
                }
            }
        });
    }
}
