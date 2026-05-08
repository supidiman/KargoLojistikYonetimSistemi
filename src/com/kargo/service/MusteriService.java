package com.kargo.service;

import com.kargo.dao.IMusteriDAO;
import com.kargo.dao.MusteriDAO;
import com.kargo.model.Musteri;
import java.util.List;

public class MusteriService {
    private IMusteriDAO dao;

    public MusteriService() {
        this.dao = new MusteriDAO();
    }

    // Arayüzden gelen verinin doğruluğunu kontrol eden validasyon metodu
    public void validation(Musteri m) throws Exception {
        if (m.getAd() == null || m.getAd().trim().isEmpty()) {
            throw new Exception("Müşteri adı boş bırakılamaz!");
        }
        if (m.getTc() == null || m.getTc().length() != 11) {
            throw new Exception("TC Kimlik Numarası tam 11 haneli olmalıdır!");
        }
    }

    public void musteriKaydet(Musteri m) {
        try {
            validation(m); // Önce kontrol et
            dao.ekle(m);   // Sorun yoksa kaydet
            System.out.println("Müşteri başarıyla kaydedildi.");
        } catch (Exception e) {
            System.out.println("Kayıt Hatası: " + e.getMessage());
        }
    }

    public void MusteriGuncelle(Musteri m) {
        try {
            validation(m);
            dao.guncelle(m);
            System.out.println("Müşteri başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme Hatası: " + e.getMessage());
        }
    }

    public List<Musteri> Musterilerigetir() {
        return dao.listele();
    }
}