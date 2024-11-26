package entity.efffect;
import java.awt.image.BufferedImage;
import untilz.LoadSave;

public enum EffectType {

    FIRE_WORK("effect_fire_work", LoadSave.EFFECT_FIRE_WORK, 98, 86, 73),
    GOT_HIT("got_hit", LoadSave.EFFECT_GOT_HIT, 100, 100, 64),
    SHIELD("shield", LoadSave.EFFECT_SHIELD, 556, 556, 1);

    private int width;
    private int height;
    private String name;
    private String filePath;
    private BufferedImage[] animation;


    EffectType(String name, String filePath,int width, int height, int spriteCount) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.filePath = filePath;
        this.animation = LoadSave.getSpriteAnimations(filePath, width, height, spriteCount);
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage[] getAnimations() {
        return animation;
    }
}
