package unity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Collider2D {

    private Rectangle2D.Float hitBox;

    private static float DISTANCE_THRESHOLD = 0.0005f;
    public boolean isTriger = true;

    public Collider2D(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    public void drawHitBox(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        g2d.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public void updateHitBox(Vector2D position) {
        hitBox.x = (int) position.x;
        hitBox.y = (int) position.y;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public static boolean OnCollison2D(Collider2D a, Collider2D b) {
        Rectangle2D.Float boxA = a.getHitBox();
        Rectangle2D.Float boxB = b.getHitBox();
       
        boolean collisionX = Math.abs( (boxA.x + boxA.width / 2) - (boxB.x + boxB.width / 2))
        < (boxA.width + boxB.width) / 2 + DISTANCE_THRESHOLD;
        boolean collisionY = Math.abs( (boxA.y + boxA.height / 2) - (boxB.y + boxB.height / 2)) 
        < (boxA.height + boxB.height) / 2 + DISTANCE_THRESHOLD;

        return collisionX && collisionY;
    }
}
