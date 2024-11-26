package entity.item;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.star.Star;
import main.Game;
import unity.Vector2D;

public class ShootingStar extends Item {
    private List<Star> stars;
    private static final int STAR_COUNT = 64;

    private Random random;
    private float effectDuration = 10.0f;
    private float effectTimer = 0;
    private static final int STAR_SIZE = 32;

    public ShootingStar(float x, float y) {
        super(x, y, ItemType.SHOOTING_STAR);
        stars = new ArrayList<>();
        random = new Random();
    }

    protected void onCollect() {
        super.onCollect();
        Game.getInstance().getAudioPlayer().play("happy_momment", false);
        spawnStars(0);
        spawnStars(0);
        isEffectActive = true;
        ItemState.SHOOTING_STAR.setEffect(true);

    }

    private void spawnStars(float distance) {

        int starCount = random.nextInt(STAR_COUNT, STAR_COUNT * 2);

        for (int i = 0; i < starCount; i++) {

            float starSize = random.nextFloat(STAR_SIZE * 0.8f, STAR_SIZE * 1.2f);

            float x = random.nextFloat(Game.getInstance().GAME_WIDTH, Game.getInstance().GAME_WIDTH * 2);

            float y = random.nextFloat(-Game.getInstance().GAME_HEIGHT / 2, -100);

            float adjustedX = distance + x;
            float adjustedY = y;

            Star star = new Star(adjustedX, adjustedY, (int) starSize, (int) starSize);
            star.setForce(generateStarForce());
            stars.add(star);
        }
    }

    private Vector2D generateStarForce() {

        float forceX = random.nextFloat(-25, -16);
        float forceY = random.nextFloat(14, 20);

        return new Vector2D(forceX, forceY);
    }

    public void update() {
        super.update();

        if (isEffectActive) {
            effectTimer += Game.getInstance().DELTA_TIME;

            for (Star star : stars) {
                star.setApplyGravity(true);
                star.update();

            }

            if (effectTimer >= effectDuration) {

                isEffectActive = false;
                ItemState.SHOOTING_STAR.setEffect(false);
                stars.clear();
                effectTimer = 0;
            }
        }
    }

    public void render(Graphics2D g2d) {
        super.render(g2d);

        if (isEffectActive && !stars.isEmpty()) {
            for (Star star : stars) {
                if (star.getIsActive()) {
                    star.render(g2d);
                }
            }
        }
    }

}
