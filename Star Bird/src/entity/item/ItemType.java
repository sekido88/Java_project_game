package entity.item;

import untilz.LoadSave;
import java.awt.image.BufferedImage;

public enum ItemType {
    SHOOTING_STAR(LoadSave.SHOOTING_STAR, 40, 80,0.5f),
    SHIELD(LoadSave.SHIELD, 40, 80,0.5f);

    private final String spritePath;
    private final int width;
    private final int height;
    private final float fallSpeed;

    ItemType(String spritePath, int width,int height, float fallSpeed) {
        this.spritePath = spritePath;
        this.width = width;
        this.height = height;
        this.fallSpeed = fallSpeed;
    }

    public String getSpritePath() { return spritePath; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public float getFallSpeed() { return fallSpeed; }
    public BufferedImage getSprite() {
        return LoadSave.loadImage(spritePath);
    }
}

