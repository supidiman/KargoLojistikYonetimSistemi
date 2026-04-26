package com.kargo.dao;

import com.kargo.model.Musteri;
import java.util.List;

/**
 * Müşteri veri erişim nesnesi için arayüz (Interface).
 * Bu arayüz, müşteri yönetimiyle ilgili tüm veritabanı metodlarını tanımlar.
 */
public interface IMusteriDAO {
    
    /**
     * Yeni bir müşteriyi sisteme kaydeder.
     * @param m Eklenecek olan Musteri nesnesi.
     */
    void ekle(Musteri m);
    
    /**
     * Belirtilen ID'ye sahip müşteriyi siler.
     * @param id Silinecek müşterinin ID numarası.
     */
    void sil(int id);
    
    /**
     * Mevcut bir müşterinin bilgilerini (adres, telefon vb.) günceller.
     * @param m Güncel bilgileri içeren Musteri nesnesi.
     */
    void guncelle(Musteri m);
    
    /**
     * Veritabanındaki tüm müşterileri liste halinde getirir.
     * @return Kayıtlı tüm müşterilerin listesi.
     */
    List<Musteri> listele();
}
