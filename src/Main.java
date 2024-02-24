import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private double[][] cubeVertices = {
            {-1, -1, -1},
            {-1, -1, 1},
            {-1, 1, -1},
            {-1, 1, 1},
            {1, -1, -1},
            {1, -1, 1},
            {1, 1, -1},
            {1, 1, 1}
    };

    private int[][] cubeEdges = {
            {0, 1}, {1, 3}, {3, 2}, {2, 0},
            {4, 5}, {5, 7}, {7, 6}, {6, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };

    private double angleX = 0.02;
    private double angleY = 0.02;

    public Main() {
        setTitle("Rotating cube");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateCube();
                repaint();
            }
        });
        timer.start();

    }

    private void rotateCube() {
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);

        for (int i = 0; i < cubeVertices.length; i++) {
            double x = cubeVertices[i][0];
            double y = cubeVertices[i][1];
            double z = cubeVertices[i][2];


            cubeVertices[i][0] = x * cosY - z * sinY;
            cubeVertices[i][2] = z * cosY + x * sinY;

            z = cubeVertices[i][2];

            cubeVertices[i][1] = y * cosX - z * sinX;
            cubeVertices[i][2] = z * cosX + y * sinX;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        for (int[] edge : cubeEdges) {
            int x1 = (int) (centerX + cubeVertices[edge[0]][0] * 50);
            int y1 = (int) (centerY - cubeVertices[edge[0]][1] * 50);
            int x2 = (int) (centerX + cubeVertices[edge[1]][0] * 50);
            int y2 = (int) (centerY - cubeVertices[edge[1]][1] * 50);

            g2d.drawLine(x1, y1, x2, y2);
        }
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
