package entity.efffect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.Game;
import unity.GameObject;
import unity.Vector2D;

public class Effect extends GameObject {
    private EffectType effectType;
    private BufferedImage[] animations;

    private int aniTick = 0;
    private int aniIndex = 0;
    private final int aniSpeed = 7;

    private boolean isLooping;
    private boolean isFinished;


    public Effect(float x, float y,int width, int height, EffectType effectType) {
        super(x, y, effectType.getWidth(), effectType.getHeight());
        this.effectType = effectType;
        this.animations = effectType.getAnimations();
        this.isLooping = false;
        reset();
    }

    private void reset() {
        aniTick = 0;
        aniIndex = 0;
        isFinished = false;
        isActive = false;
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= animations.length) {
                if (isLooping) {
                    aniIndex = 0;
                } else {
                    isFinished = true;
                    isActive = false;
                    aniIndex = 0;
                }
            }
        }
    }

    public void update() {
        if (!isActive)
            return;
        updateAnimationTick();
    }

    public void render(Graphics2D g2d) {
        if (!isActive)
            return;

        BufferedImage currentFrame = animations[aniIndex];
        g2d.drawImage(currentFrame, (int) position.x, (int) position.y, width, height, null);
    }

    public void play(float x, float y) {
        reset();
        setPosition(new Vector2D(x, y));
        isActive = true;
    }
    

    public boolean isFinished() {
        return isFinished;
    }

    public EffectType getEffectType() {
        return effectType;
    }
}
