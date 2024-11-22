package entity.item;

import unity.GameObject;
import unity.Rigidbody2D;
import unity.Vector2D;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import main.Game;;

public class Item extends GameObject {

    protected ItemType itemType;
    protected Rigidbody2D Rigidbody2D;
    protected BufferedImage img;

    protected boolean isEffectActive = false;

    public Item(float x, float y, ItemType itemType) {
        super(x, y, itemType.getWidth(), itemType.getHeight());
        this.itemType = itemType;
        this.img = itemType.getSprite();
        Rigidbody2D = new Rigidbody2D(1, new Vector2D(0, 0), position, y);
    }

    public void update() {
        if (!isActive)
            return;
        Rigidbody2D.applyGravity(Game.getInstance().GRAVITY.multiply(this.itemType.getFallSpeed()));
        Rigidbody2D.update();
        position = Rigidbody2D.position;
        collider2D.updateHitBox(position);

        checkCollisionPlayer();
    }

    protected void checkCollisionPlayer() {
        if (collider2D.OnCollison2D(collider2D, Game.getInstance().getPlaying().getPlayer().getCollider2D())) {
            onCollect();
        }
    }

    protected void onCollect() {
        isActive = false;
    }

    public void render(Graphics2D g2d) {

        if (isActive)
            g2d.drawImage(img, (int) position.x, (int) position.y, width, height, null);
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean getIsEffectActive() {
        return isEffectActive;
    }
}
