package entity.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import untilz.LoadSave;

import main.Game;
import unity.GameObject;
import unity.Rigidbody2D;
import unity.Vector2D;

import java.awt.geom.AffineTransform;

import java.util.List;

import game_states.Gamestate;

public class Player extends GameObject {

    // limit move
    private int minX = 0;
    private int maxX = Game.getInstance().GAME_WIDTH;
    private int minY = 0;
    private int maxY = Game.getInstance().GAME_HEIGHT;

    // animation
    private int aniTick, aniIndex;
    private int aniSpeed = 8;
    private List<BufferedImage[]> animations;
    private PlayerAction currentAction = PlayerAction.Idle;

    // physical
    private Rigidbody2D Rigidbody2D;

    // force
    private Vector2D forceToMouseClicked = new Vector2D(-5000f, -4000f);
    private float speedForce = 2f;

    // physical render
    private double rotation = 0;
    private double rotationSpeed = 0.1;

    // live
    private boolean isAlive = true;

    public enum PlayerAction {
        Idle(4), Got_Hit(2);

        private final int spriteAmount;

        PlayerAction(int spriteAmount) {
            this.spriteAmount = spriteAmount;
        }

        public int getSpriteAmount() {
            return spriteAmount;
        }
    }

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        initRigidbody2D();
        loadAnimations();

    }

    private void initRigidbody2D() {
        Rigidbody2D = new Rigidbody2D(1f, new Vector2D(0, 0), position, 1);
        Rigidbody2D.dragCoefficient = 0.2f;
    }

    private void loadAnimations() {
        animations = LoadSave.getSpriteAnimations(LoadSave.PLAYER_ANIMATIONS, PlayerAction.values());
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getCurrentActionFrames().length) {
                aniIndex = 0;
            }
        }
    }

    private void setAnimation(PlayerAction action) {
        if (currentAction != action) {
            currentAction = action;
            aniIndex = 0;
            aniTick = 0;
        }
    }

    private BufferedImage[] getCurrentActionFrames() {
        return animations.get(currentAction.ordinal());
    }

    private void drawPlayer(Graphics2D g2d) {
        BufferedImage currentSprite = getCurrentActionFrames()[aniIndex];
        if (currentSprite == null) {
            System.out.print("Failed to load player");
            return;
        }

        AffineTransform transform = new AffineTransform();
        Vector2D velocity = Rigidbody2D.velocity;

        double targetAngle = Math.atan2(velocity.y, velocity.x);
        double angleDiff = targetAngle - rotation;
        while (angleDiff > Math.PI)
            angleDiff -= 2 * Math.PI;
        while (angleDiff < -Math.PI)
            angleDiff += 2 * Math.PI;

        rotation += angleDiff * rotationSpeed;

        while (rotation > Math.PI)
            rotation -= 2 * Math.PI;
        while (rotation < -Math.PI)
            rotation += 2 * Math.PI;

        boolean shouldFlip = velocity.x < 0;

        double centerX = position.x + width / 2;
        double centerY = position.y + height / 2;

        transform.translate(centerX, centerY);

        if (shouldFlip) {
            transform.scale(1, -1);
            transform.rotate(-rotation);
        } else {
            transform.rotate(rotation);
        }

        transform.translate(-width / 2, -height / 2);
        transform.scale((float) width / currentSprite.getWidth(),
                (float) height / currentSprite.getHeight());

        g2d.drawImage(currentSprite, transform, null);
    }

    public void render(Graphics2D g2d) {
        drawPlayer(g2d);
    }

    private void updatePos() {

        Rigidbody2D.applyGravity(Game.getInstance().GRAVITY.multiply(speedForce));
        Rigidbody2D.applyDrag();
        Rigidbody2D.update();

        position = Rigidbody2D.position;

        position.x = Math.max((float) (minX), Math.min(position.x, maxX - width));
        position.y = Math.max((float) (minY), Math.min(position.y, maxY));

        if (position.x == minX || position.x == maxX - width) {
            Rigidbody2D.velocity = Rigidbody2D.velocity.multiply(-1);
        }

        Rigidbody2D.position = position;

        if (position.y >= Game.getInstance().GAME_HEIGHT) {
            Game.getInstance().setIsGameOver(false);
            Gamestate.state = Gamestate.GAME_OVER;
        }
    }

    public void update() {

        if (!isAlive) {
            setAnimation(PlayerAction.Got_Hit);
        }

        updatePos();
        collider2D.updateHitBox(position);
        updateAnimationTick();
    }

    public void mouseClickForce() {
        if (isAlive) {
            forceToMouseClicked.x *= -1;
            Rigidbody2D.applyForce(forceToMouseClicked.multiply(speedForce));
        }
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}