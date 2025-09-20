package main;

import view.MainWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainSwing {
    
    public static void main(String[] args) {
        // Configurar Look and Feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Se falhar, usa o Look and Feel padrão
            System.out.println("Usando Look and Feel padrão");
        }
        
        // Executar interface gráfica na thread apropriada
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}