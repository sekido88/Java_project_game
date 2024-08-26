package JavaGameProject.unity;

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
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
}
