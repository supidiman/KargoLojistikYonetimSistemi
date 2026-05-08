package com.kargo.dao;
import com.kargo.model.Musteri;
import java.util.List;

public interface IMusteriDAO {
    void ekle(Musteri m);
    void sil(String tc); // DUZELTME: int id yerine String tc
    void guncelle(Musteri m);
    List<Musteri> listele(); // DUZELTME: kucuk l harfi
}