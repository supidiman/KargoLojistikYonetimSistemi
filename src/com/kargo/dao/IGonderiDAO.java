package com.kargo.dao;

import com.kargo.model.Gonderi;
import java.util.List;

public interface IGonderiDAO {
    void ekle(Gonderi g);
    void sil(int id);
    void guncelle(Gonderi g);
    List<Gonderi> listele();
}