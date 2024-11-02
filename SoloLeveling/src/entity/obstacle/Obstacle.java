package entity.obstacle;

import unity.GameObject;
import unity.Rigibody2D;
import unity.Vector2D;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;
import main.Game;

public class Obstacle extends GameObject {

    private ObstacleType obstacleType;
    private Rigibody2D rigibody2d;
    private BufferedImage img;

    private long delayTime = 5000;
    private long lastTime = 0;

    public Obstacle(float x, float y, int width, int height, ObstacleType obstacleType) {
        super(x, y, width, height);
        this.obstacleType = obstacleType;
        this.img = obstacleType.getSprite();
        rigibody2d = new Rigibody2D(1, new Vector2D(0, 0), position, 1);
        lastTime = System.currentTimeMillis();
    }

    public Obstacle(float x, float y, ObstacleType obstacleType) {
        super(x, y, obstacleType.getWidth(), obstacleType.getHeight());
        this.obstacleType = obstacleType;
        this.img = obstacleType.getSprite();
        rigibody2d = new Rigibody2D(1, new Vector2D(0, 0), position, 1);
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        rigibody2d.applyGravity(Game.getInstance().GRAVITY.multiply(this.obstacleType.getFallSpeed()));
        rigibody2d.update();
        position = rigibody2d.position;
        collider2D.updateHitBox(position);

        checkCollisionPlayer();
    }

    private void checkCollisionPlayer() {

        if (collider2D.OnCollison2D(collider2D, Game.getInstance().getPlaying().getPlayer().getCollider2D())) {
            Game.getInstance().getPlaying().getPlayer().setIsAlive(false);
            Game.getInstance().getAudioPlayer().play("got_hit", false);
            Game.getInstance().setIsGameOver(true);
        }
    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(img, (int) position.x, (int) position.y, width, height, null);
    }

}
