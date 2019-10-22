package com.mf.id.solidapp.simulasiKreditScreen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mf.id.solidapp.R;
import com.mf.id.solidapp.rangkumanPerhitunganScreen.RangkumanActivity;
import com.mf.id.solidapp.simulasiKreditScreen.model.SimulasiModel;

import org.apache.poi.ss.formula.functions.FinanceLib;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SimulasiAdapter extends RecyclerView.Adapter<SimulasiAdapter.ViewHolder> {

    List<SimulasiModel> list;
    Long pokok;
    Context context;

    public SimulasiAdapter(Context context,List<SimulasiModel> list,long pokok) {
        this.list = list;
        this.context = context;
        this.pokok = pokok;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_simulasi_kredit,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final Long angsuranV = Math.round(FinanceLib.pmt((list.get(i).getBunga()/100)/12,list.get(i).getDurasi(),0-pokok,0,false)/100)*100;

        viewHolder.angsuran.setText("Rp. " + NumberFormat.getNumberInstance(new Locale("in","ID")).format(angsuranV));
        viewHolder.tenor.setText(list.get(i).getDurasi()+ " Bulan");
        viewHolder.detail.setImageResource(R.drawable.ic_search);
        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, RangkumanActivity.class);
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                x.putExtra("pokok", pokok);
                x.putExtra("angsuran", angsuranV);
                x.putExtra("bunga", list.get(i).getBunga());
                x.putExtra("durasi", list.get(i).getDurasi());
                context.startActivity(x);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenor, angsuran;
        ImageView detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenor = itemView.findViewById(R.id.tenorTV);
            angsuran = itemView.findViewById(R.id.angsuranTV);
            detail = itemView.findViewById(R.id.detailIV);
        }
    }
}
