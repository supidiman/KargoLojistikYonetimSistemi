package com.kargo.dao;

import com.kargo.model.Personel;

public interface IPersonelDAO {
    boolean personelDogrula(String kullaniciAdi, String sifre);
    void ekle(Personel p);
}