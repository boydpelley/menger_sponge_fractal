import java.util.ArrayList;

public class Box {
    Vector3D pos;
    float r;

    private double angleX = 0.02;
    private double angleY = 0.02;

    Box(float x, float y, float z, float r) {
        pos = new Vector3D(x, y, z);
        this.r = r;
    }

    public ArrayList<Box> generate() {
        ArrayList<Box> boxes = new ArrayList<>();

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    float newR = r / 3;
                    Box b = new Box(pos.getX() + x * newR, pos.getY() + y * newR, pos.getZ() + z * newR, newR);
                    boxes.add(b);
                }
            }
        }

        return boxes;
    }

    public void rotateCube() {
        // Rotate the box around X-axis
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        float rotatedY = (float) (pos.getY() * cosX + pos.getZ() * sinX);
        float rotatedZ = (float) (-pos.getY() * sinX + pos.getZ() * cosX);

        // Rotate the box around Y-axis
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);
        float rotatedX = (float) (pos.getX() * cosY + rotatedZ * sinY);
        float rotatedZFinal = (float) (-pos.getX() * sinY + rotatedZ * cosY);

        // Update the box position
        pos.setX(rotatedX);
        pos.setY(rotatedY);
        pos.setZ(rotatedZFinal);
    }

    public float[] getVertices() {
        float[] vertices = new float[24];

        for (int i = 0; i < 8; i++) {
            vertices[i * 3] = pos.getX();
            vertices[i * 3 + 1] = pos.getY();
            vertices[i * 3 + 2] = pos.getZ();
        }

        return vertices;
    }
}

