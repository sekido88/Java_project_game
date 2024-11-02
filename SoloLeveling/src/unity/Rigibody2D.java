package unity;

import main.Game;

public class Rigibody2D {

    public float mass = 1f;
    public Vector2D velocity;
    public Vector2D acceleration = new Vector2D(0, 0);;
    public Vector2D position;

    public float dragCoefficient = 0.2f;
    public float area = 1;
    public float airDensity = 0.1f;

    public Rigibody2D(float mass, Vector2D velocity, Vector2D position, float area) {
        this.position = position;
        this.mass = mass;
        this.velocity = velocity;
        this.area = area;
    }

    public void applyForce(Vector2D force) {
        Vector2D newAccelaraion = force.divide(mass);
        acceleration = acceleration.add(newAccelaraion);
    }

    public void applyGravity(Vector2D gravity) {
        applyForce(gravity.multiply(mass));
    }

    public void applyDrag() {
        // f_drag = -0.5 * C_d * p * A * v^2;
        /*
         * c_d he so can
         * p mat do khong khi
         * A dien tich be mat
         * v la van toc cua vat
         */
        float velocityMagnitude = velocity.magnitude();
        Vector2D dragForce = velocity.normalize()
                .multiply(-0.5f * airDensity * dragCoefficient * velocityMagnitude * velocityMagnitude);
        applyForce(dragForce);
    }

    public void update() {

        velocity = velocity.add(acceleration.multiply((float) Game.getInstance().DELTA_TIME));
        position = position.add(velocity.multiply((float) Game.getInstance().DELTA_TIME));
        acceleration = new Vector2D(0, 0);
    }

}
