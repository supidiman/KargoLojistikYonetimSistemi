package com.kargo.model;

public class StandartKargo extends Gonderi {

    public StandartKargo(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        super(id, agirlik, mesafe, durum, musteri);
    }

    @Override
    public double ucretHesapla() {
        // Örnek: Ağırlık başına 10 TL, kilometre başına 2 TL
        return (getAgirlik() * 10) + (getMesafe() * 2);
    }
}