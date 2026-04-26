package com.kargo.model;

public class StandartKargo extends Gonderi {
    public StandartKargo() { super(); }
    public StandartKargo(double agirlik, double mesafe) { super(agirlik, mesafe); }

    @Override
    public double ucretHesapla() {
        return (getAgirlik() * 10) + (getMesafe() * 2);
    }
}
