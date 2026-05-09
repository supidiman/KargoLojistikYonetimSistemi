# 🚚 Kargo Lojistik Yönetim Sistemi

Modern lojistik operasyonlarını dijital ortamda yönetmek amacıyla geliştirilen kapsamlı bir **Kargo ve Lojistik Yönetim Sistemi**.  
Bu proje; kullanıcı yönetimi, operasyon süreçleri, veri tabanı yönetimi ve nesne yönelimli yazılım mimarisi prensipleri temel alınarak geliştirilmiştir.

## 🎯 Proje Amacı

Bu sistemin temel amacı:

- Kargo operasyonlarını dijitalleştirmek
- Kullanıcı ve personel yönetimini kolaylaştırmak
- Sipariş ve sevkiyat süreçlerini merkezi şekilde yönetmek
- Modüler ve sürdürülebilir bir yazılım mimarisi oluşturmak
- OOP prensiplerine uygun ölçeklenebilir bir yapı sunmaktır

---

# 🛠️ Kullanılan Teknolojiler

- Java
- Java Swing
- JDBC
- SQLite
- DAO Design Pattern

---

# 📂 Proje Mimarisi

```bash
KargoLojistikYonetimSistemi
│
├── src/
│   └── com/
│       └── kargo/
│           │
│           ├── model/
│           │   ├── Gonderi.java
│           │   ├── StandartKargo.java
│           │   ├── HizliKargo.java
│           │   ├── UluslararasiKargo.java
│           │   ├── Musteri.java
│           │   ├── Personel.java
│           │   ├── Ucretlendirme.java
│           │   └── Durum.java
│           │
│           ├── dao/
│           │   ├── DatabaseConnection.java
│           │   ├── IGonderiDAO.java
│           │   ├── IMusteriDAO.java
│           │   ├── IPersonelDAO.java
│           │   ├── GonderiDAO.java
│           │   ├── MusteriDAO.java
│           │   └── PersonelDAO.java
│           │
│           ├── service/
│           │   ├── KargoService.java
│           │   ├── MusteriService.java
│           │   └── AuthService.java
│           │
│           ├── ui/
│           │   ├── MainFrame.java
│           │   ├── LoginFrame.java
│           │   ├── RegisterFrame.java
│           │   ├── KargoPanel.java
│           │   ├── MusteriPanel.java
│           │   ├── ConsumerListInternalFrame.java
│           │   └── RegisterInternalFrame.java
│           │
│           └── Main.java
│
├── lib/
│   └── sqlite-jdbc-3.53.1.0.jar
│
├── kargo.db
└── README.md
