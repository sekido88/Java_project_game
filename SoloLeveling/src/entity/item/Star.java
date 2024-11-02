package entity.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

import main.Game;
import unity.Collider2D;
import unity.GameObject;
import unity.Rigibody2D;
import unity.Vector2D;
import untilz.LoadSave;

public class Star extends GameObject {

    private BufferedImage imgStar;
    public float rotationAngle = 0;
    private Rigibody2D rigibody2d;
    private boolean isApplyGravity = false;
    private Vector2D force;

    Star(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadImage();
        rigibody2d = new Rigibody2D(1, new Vector2D(0, 0), position, 1);
    }

    private void loadImage() {
        imgStar = LoadSave.loadImage(LoadSave.STAR);
    }

    public void render(Graphics2D g2d) {

        AffineTransform transform = new AffineTransform();
        transform.translate(position.x + width / 2, position.y + height / 2);
        transform.rotate(Math.toRadians(rotationAngle));
        transform.translate(-width / 2, -height / 2);

        double scaleX = (double) width / imgStar.getWidth();
        double scaleY = (double) height / imgStar.getHeight();
        transform.scale(scaleX, scaleY);

        g2d.drawImage(imgStar, transform, null);

    }

    private void applyForce() {
        rigibody2d.applyForce(force);
        rigibody2d.update();
        position = rigibody2d.position;
    }

    private void checkCollisonWithPlayer() {
        if (Collider2D.OnCollison2D(collider2D, Game.getInstance().getPlaying().getPlayer().getCollider2D())
                && isActive) {
            Game.getInstance().getAudioPlayer().play("coin_tap", false);
            Game.getInstance().getPlaying().getScore().addScore(1);
            isActive = false;
        }
    }

    public void update() {
        rotationAngle += 15 * Game.getInstance().DELTA_TIME;

        if (isApplyGravity) {
            applyForce();
        }

        collider2D.updateHitBox(position);
        checkCollisonWithPlayer();
    }

    public void setForce(Vector2D force) {
        this.force = force;
    }

    public void setApplyGravity(boolean isApplyGravity) {
        this.isApplyGravity = isApplyGravity;
    }

    public boolean getIsApllyGravity() {
        return isApplyGravity;
    }

    public Rigibody2D getRigibody2d() {
        return this.rigibody2d;
    }

}
