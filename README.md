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
│   └── com/
│       └── kargo/
│           │
│           ├── model/
│           │   ├── Gonderi.java
│           │   ├── StandartKargo.java
│           │   ├── HizliKargo.java
│           │   ├── UluslararasiKargo.java
│           │   ├── Musteri.java
│           │   ├── Personel.java
│           │   ├── Ucretlendirme.java
│           │   └── Durum.java
│           │
│           ├── dao/
│           │   ├── DatabaseConnection.java
│           │   ├── IGonderiDAO.java
│           │   ├── IMusteriDAO.java
│           │   ├── IPersonelDAO.java
│           │   ├── GonderiDAO.java
│           │   ├── MusteriDAO.java
│           │   └── PersonelDAO.java
│           │
│           ├── service/
│           │   ├── KargoService.java
│           │   ├── MusteriService.java
│           │   └── AuthService.java
│           │
│           ├── ui/
│           │   ├── MainFrame.java
│           │   ├── LoginFrame.java
│           │   ├── RegisterFrame.java
│           │   ├── KargoPanel.java
│           │   ├── MusteriPanel.java
│           │   ├── ConsumerListInternalFrame.java
│           │   └── RegisterInternalFrame.java
│           │
│           └── Main.java
│
├── lib/
│   └── sqlite-jdbc-3.53.1.0.jar
│
├── kargo.db
└── README.md
```

---

# ⚙️ Sistem Özellikleri

✅ Kullanıcı giriş ve doğrulama sistemi  
✅ Kargo oluşturma ve takip işlemleri  
✅ Personel ve müşteri yönetimi  
✅ Veritabanı bağlantısı ve CRUD işlemleri  
✅ DAO mimarisi ile veri erişim katmanı  
✅ Modüler ve sürdürülebilir kod yapısı  
✅ Nesne yönelimli programlama prensiplerine uygun tasarım

---

# 🧩 Yazılım Mimarisi

Projede katmanlı yazılım mimarisi kullanılmıştır:

- UI Layer → Kullanıcı arayüzü işlemleri
- Service Layer → İş mantığı yönetimi
- DAO Layer → Veritabanı erişim işlemleri
- Model Layer → Veri nesneleri

Bu yapı sayesinde:

- Kod tekrarının azaltılması
- Yönetilebilirlik
- Test edilebilirlik
- Ölçeklenebilirlik

hedeflenmiştir.


```
# ⚙️ Kurulum ve Çalıştırma

### 1. Projeyi İndirin

git clone https://github.com/supidiman/KargoLojistikYonetimSistemi.git

GitHub sayfasındaki yeşil **Code → Download ZIP** seçeneğiyle de indirebilirsiniz.

### 2. IntelliJ IDEA ile Açın
**File → Open** seçeneğiyle indirilen `KargoLojistikYonetimSistemi` klasörünü açın.

### 3. JAR Dosyasını Ekleyin
1. **File → Project Structure → Modules → Dependencies** sekmesine gelin.
2. **+** butonuna tıklayın → **JARs or Directories** seçin.
3. `lib/sqlite-jdbc-3.53.1.0.jar` dosyasını seçin → **Apply → OK**.

### 4. Çalıştırın
`src → com → kargo → Main.java` dosyasını açıp sağ üstteki ▶ **Run** butonuna basın.

> `kargo.db` veritabanı ilk çalıştırmada otomatik olarak oluşturulur, ek bir ayar gerekmez.

```

# 🗄️ Veritabanı

Sistem JDBC bağlantısı kullanarak SQL tabanlı bir veritabanı ile haberleşmektedir.

Örnek tablolar:

- Kullanicilar
- Kargolar
- Personeller
- Musteriler
- Sevkiyatlar

---

# 📌 Gelecek Geliştirmeler

- Gerçek zamanlı kargo takip sistemi
- QR / Barkod entegrasyonu
- REST API desteği
- Yetkilendirme sistemi geliştirmeleri
- Mobil uygulama desteği
- Raporlama ve analiz modülleri

---

# 📖 Yazılım Tasarım Yaklaşımları

Projede aşağıdaki yazılım geliştirme yaklaşımları uygulanmıştır:

- Object-Oriented Programming (OOP)
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- DAO Design Pattern
- Layered Architecture

---

# 🤝 Katkıda Bulunma

Projeye katkıda bulunmak için:

1. Fork oluşturun
2. Yeni branch açın
3. Değişikliklerinizi commit edin
4. Pull Request gönderin

---

# 📄 Lisans

Bu proje eğitim ve akademik kullanım amacıyla geliştirilmiştir.

---

# 👥 Proje Ekibi (Grup 24)

### Kasım Can Yıldırım
**OOP Mimari ve Core Logic**

### Yunus Emre Sarıbacak
**Veritabanı Mimari ve DAO**

### Yusuf Kağan Kızılpınar
**Kullanıcı Yönetimi ve Arayüz Entegrasyonu**

### Furkan Tayyip Arfat
**Operasyonel Modüller ve Backend Mantığı**
