package com.kargo.service;

import com.kargo.dao.IGonderiDAO;
import com.kargo.dao.GonderiDAO;
import com.kargo.model.Gonderi;
import java.util.List;

public class KargoService {
    private IGonderiDAO dao;

    public KargoService() {
        // Arayüz (Interface) referansı üzerinden gerçek nesneyi oluşturuyoruz
        this.dao = new GonderiDAO(); 
    }

    public void kargoEkle(Gonderi g) {
        // OOP Polymorphism burada devreye giriyor, hangi kargo tipiyse onun ucretini hesaplar
        double ucret = g.ucretHesapla(); 
        System.out.println("Hesaplanan Kargo Ücreti: " + ucret + " TL");
        
        // İş mantığı tamamsa veritabanına gönder
        dao.ekle(g);
    }

    public List<Gonderi> kargoListele() {
        return dao.listele();
    }
    
    // Kargo statü güncellemeleri senin sorumluluğunda olduğu için bu metodu da ekliyoruz
    public void kargoDurumGuncelle(Gonderi g) {
        dao.guncelle(g);
    }
}