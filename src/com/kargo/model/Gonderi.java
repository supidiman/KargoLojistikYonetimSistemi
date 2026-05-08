package com.kargo.model;

public abstract class Gonderi implements Ucretlendirme {
    private int id;
    private double agirlik;
    private double mesafe;
    private Durum durum;
    private Musteri musteri; // Kargonun sahibi [cite: 120]

    // Constructor (Yapıcı Metot)
    public Gonderi(int id, double agirlik, double mesafe, Durum durum, Musteri musteri) {
        this.id = id;
        this.agirlik = agirlik;
        this.mesafe = mesafe;
        this.durum = durum;
        this.musteri = musteri;
    }

    // UML'de belirtilen Durum Güncelleme Metodu [cite: 122]
    public void durumGuncelle(Durum d) {
        this.durum = d;
    }

    // Getter ve Setter metotları
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