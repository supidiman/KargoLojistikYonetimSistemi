package com.kargo.service;

import java.util.List;
import com.kargo.dao.IMusteriDAO;
import com.kargo.model.Musteri;

public class MusteriService {
    private final IMusteriDAO musteridao;

    public MusteriService(IMusteriDAO musteridao) {
        this.musteridao = musteridao;
    }
    
    public void musteriKaydet(Musteri m) {
        this.validation(m);
        try {
            musteridao.ekle(m);
        } catch(Exception ex) {
            throw new RuntimeException("Veri tabanina kayit sirasinda hata: " + ex.getMessage());
        }
    }

    public void validation(Musteri guncelmusteri) {
        if (guncelmusteri==null) throw new IllegalArgumentException("Nesne bos olmamali");
        if(guncelmusteri.getAd()==null || guncelmusteri.getAd().trim().isEmpty()) throw new IllegalArgumentException("Ad bos olamaz");
        if(guncelmusteri.getTc()==null || !guncelmusteri.getTc().matches("\\d{11}")) throw new IllegalArgumentException("TC 11 hane olmali");
        if(guncelmusteri.getTelefon()==null || !guncelmusteri.getTelefon().matches("05\\d{9}")) throw new IllegalArgumentException("Telefon formati hatali");
        if(guncelmusteri.getAdres() == null || guncelmusteri.getAdres().isBlank()) throw new IllegalArgumentException("Adres bos olamaz");
    }
    
    public void musteriGuncelle(Musteri guncelmusteri) {  
        this.validation(guncelmusteri);
        try {
            musteridao.guncelle(guncelmusteri);
        } catch(Exception ex) {
            throw new RuntimeException("Guncelleme hatasi: " + ex.getMessage());
        }
    }
    
    public List<Musteri> musterileriGetir() { 
        try {
            return musteridao.listele();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void musteriSil(String tc) {
        if(tc == null || tc.trim().isEmpty()) throw new IllegalArgumentException("TC bos olamaz!");
        try {
            musteridao.sil(tc);
        } catch(Exception ex) {
            throw new RuntimeException("Silme hatasi: " + ex.getMessage());
        }
    }
}