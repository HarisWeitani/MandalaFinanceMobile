package com.solid.aplikasisolid.simulasiKreditScreen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.solid.aplikasisolid.R;
import com.solid.aplikasisolid.rangkumanPerhitunganScreen.RangkumanActivity;
import com.solid.aplikasisolid.simulasiKreditScreen.model.SimulasiModel;

import java.util.ArrayList;
import java.util.List;

public class SimulasiAdapter extends RecyclerView.Adapter<SimulasiAdapter.ViewHolder> {

    List<SimulasiModel> list;
    Context context;

    public SimulasiAdapter(Context context,List<SimulasiModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_simulasi_kredit,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.angsuran.setText(list.get(i).getAngsuran());
        viewHolder.tenor.setText(list.get(i).getTenor());
        viewHolder.detail.setImageResource(R.drawable.ic_search_black_24dp);
        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RangkumanActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
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
