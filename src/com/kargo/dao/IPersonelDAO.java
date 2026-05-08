package com.kargo.dao;

import com.kargo.model.Personel;

public interface IPersonelDAO {
    boolean personelDogrula(String kadi, String sifre);
    void ekle(Personel p);
}