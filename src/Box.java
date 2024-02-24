import java.util.ArrayList;

public class Box {
    Vector3D pos;
    float r;

    private final double angleX = 0.02;
    private final double angleY = 0.02;

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

    Box(float x, float y, float z, float r) {
        pos = new Vector3D(x, y, z);
        this.r = r;
    }

    public double[][] getCubeVertices() {
        return cubeVertices;
    }

    public int[][] getCubeEdges() {
        return cubeEdges;
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



}
