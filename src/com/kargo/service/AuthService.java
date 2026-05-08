package com.kargo.service;

import com.kargo.dao.IPersonelDAO;
import com.kargo.dao.PersonelDAO;
import com.kargo.model.Personel;

public class AuthService {
    private IPersonelDAO dao;

    public AuthService() {
        this.dao = new PersonelDAO();
    }

    // Şifre ve kullanıcı adı kurallarını denetleyen private metot
    private boolean gecerlimi(Personel p) {
        if (p.getKullaniciAdi() == null || p.getKullaniciAdi().trim().isEmpty()) {
            return false;
        }
        if (p.getSifre() == null || p.getSifre().length() < 4) {
            return false;
        }
        return true;
    }

    public boolean register(Personel p) {
        if (gecerlimi(p)) {
            dao.ekle(p);
            System.out.println("Personel kaydı başarılı.");
            return true;
        } else {
            System.out.println("Hata: Kullanıcı adı boş olamaz ve şifre en az 4 haneli olmalıdır.");
            return false;
        }
    }

    public boolean login(String u, String p) {
        // DAO katmanına soruyor: Bu kullanıcı adı ve şifre veritabanında var mı?
        boolean basarili = dao.personelDogrula(u, p);
        if (basarili) {
            System.out.println("Giriş başarılı. Sisteme hoş geldiniz!");
        } else {
            System.out.println("Hatalı kullanıcı adı veya şifre!");
        }
        return basarili;
    }
}