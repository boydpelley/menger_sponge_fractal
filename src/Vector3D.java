import java.util.Vector;

public class Vector3D {
    private float x;
    private float y;
    private float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void add(Vector3D other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void subtract(Vector3D other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public void multiply(Vector3D other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;
    }

    public void multiplyScalar(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public void normalizeVector() {
        float magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
    }

}
