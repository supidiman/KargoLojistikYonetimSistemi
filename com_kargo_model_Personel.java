package com.kargo.model;

public class Personel {
    private int id;
    private String kullaniciAdi;
    private String sifre;

    public Personel() {}
    public Personel(String kullaniciAdi, String sifre) {
        this.kullaniciAdi = kullaniciAdi; this.sifre = sifre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getKullaniciAdi() { return kullaniciAdi; }
    public void setKullaniciAdi(String kullaniciAdi) { this.kullaniciAdi = kullaniciAdi; }
    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }
}
