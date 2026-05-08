package com.kargo.dao;

import com.kargo.model.Musteri;
import java.util.List;

public interface IMusteriDAO {
    void ekle(Musteri m);
    void sil(String tc);
    void guncelle(Musteri m);
    List<Musteri> listele();
}