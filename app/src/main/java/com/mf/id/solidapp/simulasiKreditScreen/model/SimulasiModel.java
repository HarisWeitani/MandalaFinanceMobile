package com.mf.id.solidapp.simulasiKreditScreen.model;

public class SimulasiModel {
    int durasi;
    double bunga;

    public SimulasiModel(int durasi, double bunga) {
        this.durasi = durasi;
        this.bunga = bunga;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }
}
