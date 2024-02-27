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
        float halfSize = r / 2;
        float[] vertices = new float[24];

        vertices[0] = pos.getX() - halfSize;
        vertices[1] = pos.getY() - halfSize;
        vertices[2] = pos.getZ() + halfSize;

        vertices[3] = pos.getX() + halfSize;
        vertices[4] = pos.getY() - halfSize;
        vertices[5] = pos.getZ() + halfSize;

        vertices[6] = pos.getX() + halfSize;
        vertices[7] = pos.getY() + halfSize;
        vertices[8] = pos.getZ() + halfSize;

        vertices[9] = pos.getX() - halfSize;
        vertices[10] = pos.getY() + halfSize;
        vertices[11] = pos.getZ() + halfSize;

        vertices[12] = pos.getX() - halfSize;
        vertices[13] = pos.getY() - halfSize;
        vertices[14] = pos.getZ() - halfSize;

        vertices[15] = pos.getX() + halfSize;
        vertices[16] = pos.getY() - halfSize;
        vertices[17] = pos.getZ() - halfSize;

        vertices[18] = pos.getX() + halfSize;
        vertices[19] = pos.getY() + halfSize;
        vertices[20] = pos.getZ() - halfSize;

        vertices[21] = pos.getX() - halfSize;
        vertices[22] = pos.getY() + halfSize;
        vertices[23] = pos.getZ() - halfSize;

        return vertices;
    }
}

