import java.util.ArrayList;

public class Box {
    private Vector3D pos;
    private float r;

    private final double angleX = 0.02;
    private final double angleY = 0.02;

    private double[][] cubeVertices = {
            {-1, -1, -1}, {-1, -1, 1}, {-1, 1, -1}, {-1, 1, 1},
            {1, -1, -1}, {1, -1, 1}, {1, 1, -1}, {1, 1, 1}
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

    public void generateMengerSponge(int level) {
        ArrayList<double[]> newVertices = new ArrayList<>();
        ArrayList<int[]> newEdges = new ArrayList<>();

        for (int i = 0; i < cubeEdges.length; i++) {
            divideEdge(cubeVertices[cubeEdges[i][0]], cubeVertices[cubeEdges[i][1]], level, newVertices, newEdges);
        }

        cubeVertices = newVertices.toArray(new double[0][0]);
        cubeEdges = newEdges.toArray(new int[0][0]);
    }

    private void divideEdge(double[] p1, double[] p2, int level, ArrayList<double[]> vertices, ArrayList<int[]> edges) {
        if (level == 0) {
            int index1 = vertices.size();
            vertices.add(p1.clone());
            int index2 = vertices.size();
            vertices.add(p2.clone());

            int[] newEdge = {index1, index2};
            edges.add(newEdge);
        } else {
            double[] midPoint = {(p1[0] + p2[0]) / 3, (p1[1] + p2[1]) / 3, (p1[2] + p2[2]) / 3};

            divideEdge(p1, midPoint, level - 1, vertices, edges);
            divideEdge(midPoint, p2, level - 1, vertices, edges);
        }
    }
}
