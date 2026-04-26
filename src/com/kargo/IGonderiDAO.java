package com.kargo.dao;

import com.kargo.model.Gonderi;
import java.util.List;

/**
 * Gönderi (Kargo) veri erişim nesnesi için arayüz (Interface).
 * DAO (Data Access Object) tasarım deseninin temelini oluşturur.
 * Veritabanı işlemlerinin standartlarını (sözleşmesini) belirler.
 */
public interface IGonderiDAO {
    
    // Yeni bir kargoyu veritabanına ekleme
    void ekle(Gonderi g);
    
    // Verilen ID'ye sahip kargoyu veritabanından silme
    void sil(int id);
    
    // Mevcut bir kargonun bilgilerini (örneğin durumunu) güncelleme
    void guncelle(Gonderi g);
    
    // Veritabanındaki tüm kargoları liste halinde getirme
    List<Gonderi> listele();
}