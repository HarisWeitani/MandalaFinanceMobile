package com.mf.id.solidapp.homeScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import com.mf.id.solidapp.Util.database.AppDatabase;
import com.mf.id.solidapp.simulasiKreditScreen.SimulasiKreditActivity;
import com.mf.id.solidapp.splashScreen.SplashActivity;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    Toolbar tb;
    EditText pokok;
    Spinner jenis, tahun;
    Button hitung;
    String prefix;
    double pokokV;
    int maxUsia, selectedJenis, selectedTahun;
    boolean sync;

    AppDatabase db;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

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
        sync = getIntent().getBooleanExtra("sync",true);

        sp = getSharedPreferences(getString(R.string.SPname), MODE_PRIVATE);
        edit = sp.edit();

        db = AppDatabase.getInstance(getApplicationContext());

        prefix = "Rp. ";

        selectedJenis = 0;
        selectedTahun = 0;

        pokok.setText(prefix + 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(sync)
            getMenuInflater().inflate(R.menu.home_menu, menu);
        else
            getMenuInflater().inflate(R.menu.home_menu_disconnect,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logo) {
            Intent i = new Intent(getBaseContext(), SplashActivity.class);
            i.putExtra("fromHome", true);
            startActivity(i);
            finish();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        maxUsia = 0;

        pokok.setSelection(pokok.getText().length());
        pokok.addTextChangedListener(setTextWatcher());

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                listJenis = db.userDao().getJenis();

                List<String> temp = db.userDao().getUsia();
                for (String usia : temp) {
                    usia = usia.replace("Tahun", "");
                    List<String> x = Arrays.asList(usia.split("[<> -]"));
                    for (String usiaV : x) {
                        if (!usiaV.equals("")) {
                            int i = Integer.parseInt(usiaV);
                            if(i > maxUsia)
                                maxUsia = i;
                        }
                    }
                }
                if (maxUsia>0) {
                    listTahun = new ArrayList<>();
                    maxUsia -= 1;
                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    for (int tempYear = year; tempYear >= year - maxUsia; tempYear--) {
                        listTahun.add(tempYear);
                    }
                    runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    ArrayAdapter jenisAdapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, listJenis);
                                    jenis.setAdapter(jenisAdapter);
                                    jenis.setSelection(selectedJenis);
                                    ArrayAdapter tahunAdapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, listTahun);
                                    tahun.setAdapter(tahunAdapter);
                                    tahun.setSelection(selectedTahun);
                                }
                            }
                    );
                }
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokokV < 50000000) {
                    pokok.setError("Pokok pinjaman minimal 50 juta");
                } else if (pokokV > 200000000) {
                    pokok.setError("Pokok pinjaman maximal 200 juta");
                } else {
                    selectedJenis = jenis.getSelectedItemPosition();
                    selectedTahun = tahun.getSelectedItemPosition();
                    String pokokPinjaman = pokok.getText().toString();
                    String selectedJenis = jenis.getSelectedItem().toString();
                    String selectedTahun = tahun.getSelectedItem().toString();
                    if(jenis.getSelectedItemPosition() == 1){
                        maxUsia -= 5;
                    }

                    Intent i = new Intent(getBaseContext(), SimulasiKreditActivity.class);
                    i.putExtra("jenis", selectedJenis);
                    i.putExtra("pokok", pokokPinjaman);
                    i.putExtra("tahun", selectedTahun);
                    i.putExtra("maxBeda", maxUsia + 1);
                    startActivity(i);
                }
            }
        });
    }

    //text watcher for pokok pinjaman
    TextWatcher setTextWatcher(){
        TextWatcher x = new TextWatcher() {
            private String current = "";
            private int index;
            private boolean deletingDecimalPoint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after > 0) {
                    index = s.length() - start;
                } else {
                    index = s.length() - start - 1;
                }
                if (count > 0 && s.charAt(start) == '.') {
                    deletingDecimalPoint = true;
                } else {
                    deletingDecimalPoint = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public synchronized void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    pokok.removeTextChangedListener(this);
                    if (deletingDecimalPoint)
                        s.delete(s.length() - index - 1, s.length() - index);

                    String temp = s.toString();
                    //set non edited value
                    if (!temp.startsWith(prefix)) {
                        temp = current;
                    }
                    String v_text = temp.replace(prefix, "").replaceAll("[.]", "");
                    double v_value = 0;
                    if (v_text != null && v_text.length() > 0) {
                        v_value = Double.parseDouble(v_text);
                        pokokV = v_value;
                    }
                    //set edited value
                    String v_formated = prefix + NumberFormat.getNumberInstance(new Locale("in", "ID")).format(v_value);
                    current = v_formated;

                    pokok.setText(v_formated);
                    if (index > v_formated.length()) {
                        pokok.setSelection(v_formated.length());
                    } else if (v_formated.length() - index < prefix.length()) {
                        pokok.setSelection(index);
                    } else {
                        pokok.setSelection(v_formated.length() - index);
                    }

                    pokok.addTextChangedListener(this);
                }
            }
        };
        return x;
    }
}
