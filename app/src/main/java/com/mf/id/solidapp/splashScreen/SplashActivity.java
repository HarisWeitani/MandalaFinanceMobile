package com.mf.id.solidapp.splashScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.Util.ResponseDataModel;
import com.mf.id.solidapp.Util.ResponseModel;
import com.mf.id.solidapp.Util.api.APIClient;
import com.mf.id.solidapp.Util.api.ApiInterface;
import com.mf.id.solidapp.Util.database.AppDatabase;
import com.mf.id.solidapp.homeScreen.HomeActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    ProgressBar pb;
    TextView tv;

    SharedPreferences sp;
    SharedPreferences.Editor edit;

    boolean hasData, fromHome;

    AppDatabase db;

    ApiInterface client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tv = findViewById(R.id.splashTV);
        pb = findViewById(R.id.splashPB);
        pb.setVisibility(View.INVISIBLE);

        Intent z = getIntent();
        fromHome = z.getBooleanExtra("fromHome", false);

        sp = getSharedPreferences(getString(R.string.SPname), MODE_PRIVATE);
        edit = sp.edit();

        hasData = sp.getBoolean("hasData", false);

        db = AppDatabase.getInstance(getApplicationContext());

        client = APIClient.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.VISIBLE);
                tv.setText("Mengambil data dari server");

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (isOnline()) {
                            try {
                                Response<ResponseModel> response = client.getData().execute();
                                ResponseModel model = response.body();
                                List<ResponseDataModel> listData = model.getData();

                                db.userDao().deleteAllData();

                                for (ResponseDataModel data : listData)
                                    db.userDao().insertData(data);

                                edit.putBoolean("hasData", true);
                                edit.apply();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Data berhasil diperbarui", Toast.LENGTH_LONG).show();
                                    }
                                });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (hasData || fromHome) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet, Menggunakan data lama", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText("Tidak ada koneksi internet maupun data lama, Mohon coba beberapa saat lagi");
                                        pb.setVisibility(View.INVISIBLE);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                            }
                                        },3000);
                                    }
                                });

                            }
                        }
                    }
                });

//                new Handler().postDelayed(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }, 2000
//                );
            }
        }, 1500);
    }

    public boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
