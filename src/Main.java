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
                    b.rotateCube();
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

        // Draw the Menger Sponge
        for (Box box : sponge) {
            drawMengerSponge(g2d, centerX, centerY, box);
        }
    }

    private void drawMengerSponge(Graphics2D g2d, int centerX, int centerY, Box box) {
        if (box.r < 2) {
            float x = box.pos.getX();
            float y = box.pos.getY();
            float z = box.pos.getZ();

            int drawX = (int) (centerX + x);
            int drawY = (int) (centerY - y);

            int depth = (int) z;

            int size = (int) box.r;

            // Draw a filled cube
            drawCube(g2d, drawX - depth, drawY - depth, size, size, depth);
        } else {
            // Recursively draw the smaller boxes
            ArrayList<Box> children = box.generate();
            for (Box child : children) {
                drawMengerSponge(g2d, centerX, centerY, child);
            }
        }
    }

    private void drawCube(Graphics2D g2d, int x, int y, int width, int height, int depth) {
        g2d.drawRect(x, y, width, height);
        g2d.drawLine(x, y, x - depth, y - depth);
        g2d.drawLine(x + width, y, x + width - depth, y - depth);
        g2d.drawLine(x, y + height, x - depth, y + height - depth);
        g2d.drawLine(x + width, y + height, x + width - depth, y + height - depth);
    }

    void onClick() {
        ArrayList<Box> next = new ArrayList<>();
        for (Box b : sponge) {
            next.addAll(b.generate());
        }
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
