package com.kargo.service;

import com.kargo.dao.GonderiDAO;
import com.kargo.dao.IGonderiDAO;
import com.kargo.model.Gonderi;
import java.util.List;

public class KargoService {
    
    // UML'e göre Dependency: Service, DAO interface'ini kullanır
    private IGonderiDAO dao;

    public KargoService() {
        // Yunus'un somut DAO sınıfını çağırıyoruz
        this.dao = new GonderiDAO(); 
    }

    public void kargoEkle(Gonderi g) {
        dao.ekle(g);
    }

    public List<Gonderi> kargoListele() {
        return dao.listele();
    }
    
    public void kargoGuncelle(Gonderi g) {
        dao.guncelle(g);
    }
}