package com.kargo.model;

public class UluslararasiKargo extends Gonderi {
    private double gumrukVergisi;

    public UluslararasiKargo() { super(); }
    public UluslararasiKargo(double agirlik, double mesafe, double gumrukVergisi) {
        super(agirlik, mesafe); this.gumrukVergisi = gumrukVergisi;
    }

    public double getGumrukVergisi() { return gumrukVergisi; }
    public void setGumrukVergisi(double gumrukVergisi) { this.gumrukVergisi = gumrukVergisi; }

    @Override
    public double ucretHesapla() {
        return (getAgirlik() * 30) + (getMesafe() * 5) + gumrukVergisi;
    }
}
