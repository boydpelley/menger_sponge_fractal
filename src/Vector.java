public class Vector {
    private float x;
    private float y;

    public Vector(float x, float y) {
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

    public Vector addVector(Vector V) {
        return new Vector(x + V.x, y + V.y);
    }

    public Vector subtractVector(Vector V) {
        return new Vector(x - V.x, y - V.y);
    }

    public Vector multiplyVector(Vector V) {
        return new Vector(x * V.x, y * V.y);
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector normalizedVector() {
        float magnitude = magnitude();
        return new Vector(x / magnitude, y / magnitude);
    }
}
