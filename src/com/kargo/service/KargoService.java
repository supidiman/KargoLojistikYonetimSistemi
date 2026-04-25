package com.kargo.service;

import javax.swing.table.DefaultTableModel;

public class KargoService {
    
    // YUNUS (DAO) VE CAN (MODEL) KODLARINI YAZANA KADAR GEÇİCİ TEST METODU
    public void sahteVeriYukle(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        // Arayüzünü test edebilmen için sahte veriler
        tableModel.addRow(new Object[]{1, "Ahmet Yılmaz", 5.2, 150.0, "StandartKargo", "SUBEDE"});
        tableModel.addRow(new Object[]{2, "Ayşe Demir", 1.5, 600.0, "HizliKargo", "DAGITIMDA"});
        tableModel.addRow(new Object[]{3, "Mehmet Kaya", 12.0, 2500.0, "UluslararasiKargo", "TESLIM_EDILDI"});
    }
}