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
        float[] rotatedVertices = box.getVertices();

        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0},
                {4, 5}, {5, 6}, {6, 7}, {7, 4},
                {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        float distance = 500; // Distance from the camera to the screen

        for (int[] edge : edges) {
            float x1 = rotatedVertices[edge[0] * 3];
            float y1 = rotatedVertices[edge[0] * 3 + 1];
            float z1 = rotatedVertices[edge[0] * 3 + 2];

            float x2 = rotatedVertices[edge[1] * 3];
            float y2 = rotatedVertices[edge[1] * 3 + 1];
            float z2 = rotatedVertices[edge[1] * 3 + 2];

            // Perspective projection
            float scaleFactor = distance / (distance + z1);
            int screenX1 = Math.round(x1 * scaleFactor) + getWidth() / 2;
            int screenY1 = getHeight() / 2 - Math.round(y1 * scaleFactor);

            scaleFactor = distance / (distance + z2);
            int screenX2 = Math.round(x2 * scaleFactor) + getWidth() / 2;
            int screenY2 = getHeight() / 2 - Math.round(y2 * scaleFactor);

            g2d.drawLine(screenX1, screenY1, screenX2, screenY2);
        }
    }
}
