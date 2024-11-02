package entity.obstacle;

import untilz.LoadSave;
import java.awt.image.BufferedImage;
import java.lang.String;

public enum ObstacleType {

    // METEOR(LoadSave.METEOR, 30, 30,0.2f),
    FLAMING_METROR(LoadSave.FLAMING_METEOR, 35, 30, 0.4f);

    private final String spritePath;
    private final int width;
    private final int height;
    private final float fallSpeed;
    
    ObstacleType(String spritePath, int width, int height,float fallSpeed) {
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
