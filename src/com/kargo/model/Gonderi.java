package com.kargo.model;

public abstract class Gonderi implements Ucretlendirme {
    private int id;
    private double agirlik;
    private double mesafe;
    private Durum durum;
    private Musteri musteri;

    public Gonderi() { this.durum = Durum.SUBEDE; }
    public Gonderi(double agirlik, double mesafe) {
        this.agirlik = agirlik; this.mesafe = mesafe; this.durum = Durum.SUBEDE;
    }
    public Gonderi(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        this.id = id;
        this.agirlik = agirlik;
        this.mesafe = mesafe;
        this.durum = durum;
        this.musteri = musteri;
    }

    public abstract double ucretHesapla();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getAgirlik() { return agirlik; }
    public void setAgirlik(double agirlik) { this.agirlik = agirlik; }
    public double getMesafe() { return mesafe; }
    public void setMesafe(double mesafe) { this.mesafe = mesafe; }
    public Durum getDurum() { return durum; }
    public void setDurum(Durum durum) { this.durum = durum; }
    public Musteri getMusteri() { return musteri; }
    public void setMusteri(Musteri musteri) { this.musteri = musteri; }
}