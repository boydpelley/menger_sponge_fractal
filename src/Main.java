import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private ArrayList<Box> sponge;
    Box b;
    public Main() {
        setTitle("Rotating cube");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sponge = new ArrayList<>();

        b = new Box(0, 0, 0, 100);
        sponge.add(b);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Box b : sponge) {
                    b.rotateCube(b.pos.getX(), b.pos.getY(), b.pos.getZ());
                }
                repaint();
            }
        });
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
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
        // need to add code here
        for (Box box : sponge) {
            float x = box.pos.getX();
            float y = box.pos.getY();
            float z = box.pos.getZ();

            // Adjust the coordinates based on the perspective
            int drawX = (int) (centerX + x);
            int drawY = (int) (centerY - y);

            // Draw the cube using 3D coordinates
            int size = (int) box.r;
            g2d.drawRect(drawX, drawY, size, size);
        }
    }

    void onClick() {
        ArrayList<Box> next = sponge.get(0).generate();
        sponge = next;
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
