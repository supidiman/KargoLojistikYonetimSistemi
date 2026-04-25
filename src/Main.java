import com.kargo.ui.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame anaEkran = new MainFrame();
            anaEkran.setVisible(true);
        });
    }
}