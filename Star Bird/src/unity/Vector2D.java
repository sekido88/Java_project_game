package unity;

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(float scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D divide(float scalar) {
        return new Vector2D(this.x / scalar, this.y / scalar);
    }



    public Vector2D addForce2D(Vector2D force, float mass, float deltaTime) {
        Vector2D acceleration = force.divide(mass);
        Vector2D deltaVelocity = acceleration.multiply(deltaTime);
        return this.add(deltaVelocity);
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

 
    public Vector2D normalize() {
        float magnitude = magnitude();
        if (magnitude != 0) {
            return this.divide(magnitude);
        }
        return new Vector2D(0, 0);

    }

    public Vector2D cross(Vector2D other) {
        return new Vector2D(0, this.x * other.y - this.y * other.x);
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }
     public double length() {
        return Math.sqrt(x * x + y * y);
    }
}
