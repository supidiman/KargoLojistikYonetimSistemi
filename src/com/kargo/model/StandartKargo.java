package com.kargo.model;

public class StandartKargo extends Gonderi {
    public StandartKargo() { super(); }
    public StandartKargo(double agirlik, double mesafe) { super(agirlik, mesafe); }
    public StandartKargo(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        super(id, agirlik, mesafe, durum, musteri);
    }

    @Override
    public double ucretHesapla() {
        return (getAgirlik() * 10) + (getMesafe() * 2);
    }
}