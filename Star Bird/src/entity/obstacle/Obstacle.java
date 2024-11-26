package entity.obstacle;

import unity.GameObject;
import unity.Rigidbody2D;
import unity.Vector2D;

import java.awt.image.BufferedImage;

import entity.efffect.EffectType;
import entity.item.ItemState;

import java.awt.Graphics2D;
import main.Game;

public class Obstacle extends GameObject {

    private ObstacleType obstacleType;
    private Rigidbody2D Rigidbody2D;
    private BufferedImage img;

    private int checkCollision = 1;

    public Obstacle(float x, float y, int width, int height, ObstacleType obstacleType) {
        super(x, y, width, height);
        this.obstacleType = obstacleType;
        this.img = obstacleType.getSprite();
        Rigidbody2D = new Rigidbody2D(1, new Vector2D(0, 0), position, 1);

    }

    public Obstacle(float x, float y, ObstacleType obstacleType) {
        super(x, y, obstacleType.getWidth(), obstacleType.getHeight());
        this.obstacleType = obstacleType;
        this.img = obstacleType.getSprite();
        Rigidbody2D = new Rigidbody2D(1, new Vector2D(0, 0), position, 1);

    }

    public void update() {
        Rigidbody2D.applyGravity(Game.getInstance().GRAVITY.multiply(this.obstacleType.getFallSpeed()));
        Rigidbody2D.update();
        position = Rigidbody2D.position;
        collider2D.updateHitBox(position);

        checkCollisionPlayer();
    }

    private void checkCollisionPlayer() {

        if (collider2D.OnCollison2D(collider2D, Game.getInstance().getPlaying().getPlayer().getCollider2D()) && checkCollision == 1) {

            checkCollision = 0;
            Rigidbody2D.applyForce(new Vector2D(20, 100));
            Game.getInstance().getAudioPlayer().play("got_hit", false);
            Game.getInstance().getEffectManager().playEffect(EffectType.GOT_HIT,
                    Game.getInstance().getPlaying().getPlayer().getPosition().x,
                    Game.getInstance().getPlaying().getPlayer().getPosition().y);

            if (ItemState.SHIELD.isEffect()) {
                ItemState.SHIELD.setEffect(false);
                return;
            }

            Game.getInstance().setIsGameOver(true);
            Game.getInstance().getPlaying().getPlayer().setIsAlive(false);

        }
    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(img, (int) position.x, (int) position.y, width, height, null);
    }

}
