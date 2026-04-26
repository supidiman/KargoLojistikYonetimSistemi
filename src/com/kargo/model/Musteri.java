package com.kargo.model;

public class Musteri {
    private int id;
    private String ad;
    private String tc;
    private String telefon;
    private String adres;

    public Musteri() {}
    public Musteri(int id, String ad, String tc, String telefon, String adres) {
        this.id = id; this.ad = ad; this.tc = tc; this.telefon = telefon; this.adres = adres;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getTc() { return tc; }
    public void setTc(String tc) { this.tc = tc; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public String getAdres() { return adres; }
    public void setAdres(String adres) { this.adres = adres; }
}
