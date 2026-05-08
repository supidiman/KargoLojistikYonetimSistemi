package com.kargo.dao;

import com.kargo.model.Personel;

public interface IPersonelDAO {
    
    // Yusuf'un tam olarak aradığı metot bu!
    boolean personelDogrula(String kullaniciAdi, String sifre);
    
    // Yeni personel kaydetmek için (RegisterFrame için gerekecek)
    void ekle(Personel p);
}
