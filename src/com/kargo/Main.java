package com.kargo;

import com.kargo.ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Arayüzün (UI) güvenli ve takılmadan çalışması için SwingUtilities kullanılır
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Program başladığında ekrana LoginFrame'i getir
                new LoginFrame().setVisible(true);
            }
        });
    }
}