package com.kargo.service;
import com.kargo.dao.IPersonelDAO;
import com.kargo.model.Personel;

public class AuthService {
    private final IPersonelDAO personeldao;
    
    public AuthService(IPersonelDAO personeldao) {
        this.personeldao = personeldao;
    }

    public boolean login(String kullaniciadi, String sifre) {
        if(kullaniciadi==null || sifre ==null || kullaniciadi.trim().isEmpty()) {
            return false;
        }
        try {
            boolean personeldogrula = personeldao.personelDogrula(kullaniciadi, sifre);
            return personeldogrula;
        } catch(Exception e) {
            throw new RuntimeException("Giris islemi sirasinda hata", e);
        }
    }

    private boolean gecerlimi(Personel p) {
        return p!=null && p.getSifre()!=null && p.getSifre().length()>=4 && p.getKullaniciAdi()!=null && !p.getKullaniciAdi().trim().isEmpty();
    }
    
    public boolean register(Personel person) {
        if(gecerlimi(person)!=true) {
            throw new IllegalArgumentException("Hatali deneme lutfen kontrol ediniz");
        }
        try {
            personeldao.ekle(person);
            return true;
        } catch(Exception ex) {
            throw new RuntimeException("Kayit hatasi", ex);
        }
    }
}