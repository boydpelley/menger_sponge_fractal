import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private Box b;
    private int level = 0; // Initial level of Menger sponge

    public Main() {
        setTitle("Menger Sponge");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b = new Box(0, 0, 0, 200);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.rotateCube();
                repaint();
            }
        });
        timer.start();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                level++; // Increase the level on each click
                updateMengerSponge();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        double[][] cubeVertices = b.getCubeVertices();
        int[][] cubeEdges = b.getCubeEdges();

        for (int[] edge : cubeEdges) {
            int x1 = (int) (centerX + cubeVertices[edge[0]][0] * 50);
            int y1 = (int) (centerY - cubeVertices[edge[0]][1] * 50);
            int x2 = (int) (centerX + cubeVertices[edge[1]][0] * 50);
            int y2 = (int) (centerY - cubeVertices[edge[1]][1] * 50);

            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public void updateMengerSponge() {
        b.generateMengerSponge(level);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
