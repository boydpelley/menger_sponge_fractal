import java.util.ArrayList;

public class Box {
    Vector3D pos;
    float r;

    private final double angleX = 0.02;
    private final double angleY = 0.02;

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

    public void rotateCube(float translateX, float translateY, float translateZ) {
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);

            // Translate the box position to the origin
        float translatedX = pos.getX() - translateX;
        float translatedY = pos.getY() - translateY;
        float translatedZ = pos.getZ() - translateZ;

        // Rotate around X-axis
        float rotatedY = (float) (translatedY * cosX - translatedZ * sinX);
        float rotatedZ = (float) (translatedY * sinX + translatedZ * cosX);

        // Rotate around Y-axis
        float finalX = (float) (translatedX * cosY + rotatedZ * sinY);
        float finalZ = (float) (-translatedX * sinY + rotatedZ * cosY);

        // Translate back to the original position
        pos.setX(finalX + translateX);
        pos.setY(rotatedY + translateY);
        pos.setZ(finalZ + translateZ);
    }
}
