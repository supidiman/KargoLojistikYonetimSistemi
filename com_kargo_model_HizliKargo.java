package com.kargo.model;

public class HizliKargo extends Gonderi {
    public HizliKargo() { super(); }
    public HizliKargo(double agirlik, double mesafe) { super(agirlik, mesafe); }

    @Override
    public double ucretHesapla() {
        return ((getAgirlik() * 10) + (getMesafe() * 2)) * 1.5;
    }
}
