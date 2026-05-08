package com.kargo.service;

import com.kargo.dao.IGonderiDAO;
import com.kargo.dao.GonderiDAO;
import com.kargo.model.Gonderi;
import java.util.List;

public class KargoService {
    private IGonderiDAO dao;

    public KargoService() {
        this.dao = new GonderiDAO(); 
    }

    public void kargoEkle(Gonderi g) {
        double ucret = g.ucretHesapla(); 
        System.out.println("Hesaplanan Kargo Ücreti: " + ucret + " TL");
        dao.ekle(g);
    }

    public List<Gonderi> kargoListele() {
        return dao.listele();
    }
    
    public void kargoDurumGuncelle(Gonderi g) {
        dao.guncelle(g);
    }
}