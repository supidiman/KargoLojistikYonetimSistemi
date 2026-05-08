package com.kargo.model;

public class HizliKargo extends Gonderi {

    public HizliKargo(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        super(id, agirlik, mesafe, durum, musteri);
    }

    @Override
    public double ucretHesapla() {
        double standartUcret = (getAgirlik() * 10) + (getMesafe() * 2);
        return standartUcret * 1.5; 
    }
}