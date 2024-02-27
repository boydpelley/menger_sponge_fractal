import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Main extends JFrame {

    public Main() {
        FractalPanel fractalPanel = new FractalPanel();
        JButton growButton = new JButton("Grow Fractal");
        growButton.addActionListener(e -> {
            fractalPanel.growFractal();
            fractalPanel.repaint();
        });

        setLayout(new BorderLayout());
        add(fractalPanel, BorderLayout.CENTER);
        add(growButton, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        Timer timer = new Timer(16, e -> {
            fractalPanel.rotateFractal();
            fractalPanel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
