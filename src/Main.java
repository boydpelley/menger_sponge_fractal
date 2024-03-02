import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    Box b = new Box(0, 0, 0, 200);

    public Main() {
        setTitle("Rotating cube");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.rotateCube();
                repaint();
            }
        });
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        double [][] cubeVertices = b.getCubeVertices();
        int [][] cubeEdges = b.getCubeEdges();

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
