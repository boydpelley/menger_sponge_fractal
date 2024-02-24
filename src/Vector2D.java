import java.util.Vector;

public class Vector2D {
    private float x;
    private float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float set) {
        x += set;
    }

    public void setY(float set) {
        y += set;
    }

    public void add(Vector2D other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vector2D other) {
        x -= other.x;
        y -= other.y;
    }

    public void multiply(Vector2D other) {
        x *= other.x;
        y *= other.y;
    }

    public void scalarMultiply(float scalar) {
        x *= scalar;
        y *= scalar;
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalizedVector2D() {
        float magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
    }
}
