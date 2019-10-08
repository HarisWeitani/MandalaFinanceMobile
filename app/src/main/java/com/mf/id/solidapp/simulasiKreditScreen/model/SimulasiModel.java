package com.mf.aplikasisolid.simulasiKreditScreen.model;

public class SimulasiModel {
    String tenor;
    String angsuran;

    public SimulasiModel(String tenor, String angsuran) {
        this.tenor = tenor;
        this.angsuran = angsuran;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(String angsuran) {
        this.angsuran = angsuran;
    }
}
