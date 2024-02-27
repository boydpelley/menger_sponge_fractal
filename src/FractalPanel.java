import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FractalPanel extends JPanel {
    private ArrayList<Box> sponge;

    public FractalPanel() {
        sponge = new ArrayList<>();
        sponge.add(new Box(0, 0, 0, 200));
    }

    public void growFractal() {
        ArrayList<Box> newFractal = new ArrayList<>();
        for (Box box : sponge) {
            ArrayList<Box> generatedBoxes = box.generate();
            newFractal.addAll(generatedBoxes);
        }
        sponge = newFractal;
    }

    public void rotateFractal() {
        for (Box box : sponge) {
            box.rotateCube();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.WHITE);
        for (Box box : sponge) {
            box.rotateCube();
            drawBox(g2d, box);
        }
    }

    private void drawBox(Graphics2D g2d, Box box) {
        float[] vertices = box.getVertices();
        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0},
                {4, 5}, {5, 6}, {6, 7}, {7, 4},
                {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        for (int[] edge : edges) {
            int x1 = Math.round(vertices[edge[0] * 3]);
            int y1 = Math.round(vertices[edge[0] * 3 + 1]);
            int x2 = Math.round(vertices[edge[1] * 3]);
            int y2 = Math.round(vertices[edge[1] * 3 + 1]);

            g2d.drawLine(x1 + getWidth() / 2, getHeight() / 2 - y1, x2 + getWidth() / 2, getHeight() / 2 - y2);
        }
    }
}
