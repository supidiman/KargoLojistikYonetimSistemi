package com.kargo.model;

public class HizliKargo extends Gonderi {
    public HizliKargo() { super(); }
    public HizliKargo(double agirlik, double mesafe) { super(agirlik, mesafe); }
    public HizliKargo(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        super(id, agirlik, mesafe, durum, musteri);
    }

    @Override
    public double ucretHesapla() {
        return ((getAgirlik() * 10) + (getMesafe() * 2)) * 1.5;
    }
}