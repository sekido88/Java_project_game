package entity.item;

import java.awt.Graphics2D;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.ArrayList;
import java.util.Random;

import main.Game;
import main.GamePanel;
import unity.Collider2D;

public class StarSpawner {

    private List<Star> stars;
    private static final int STAR_COUNT = 20;
    private static final int DEFAULT_STAR_SIZE = 50;

    private static final int ADJUSTED_STAR_SIZE = DEFAULT_STAR_SIZE - 20;

    private static final int DICTANCE_HEART = 10;

    private Random random;

    private float xMinSpawn;
    private float xMaxSpawn;
    private float yMinSpawn;
    private float yMaxSpawn;

    private Star star;

    public StarSpawner() {
        this.stars = new ArrayList<>();
        this.random = new Random();
        initHeartStars(0);
        setSpaceSpawn();
        initStar();
    }

    private void setSpaceSpawn() {
        xMaxSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.1);
        xMaxSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.9);
        yMinSpawn = (float) (Game.getInstance().GAME_HEIGHT * 0.1);
        yMaxSpawn = (float) (Game.getInstance().GAME_HEIGHT * 0.8);
    }

    private void initStar() {

        float x = getRandowX(DEFAULT_STAR_SIZE);
        float y = getRandomY(DEFAULT_STAR_SIZE);

        star = new Star(x, y, DEFAULT_STAR_SIZE, DEFAULT_STAR_SIZE);

    }

    private float getRandowX(float offset) {
        float x = random.nextFloat(xMinSpawn, xMaxSpawn - offset);
        return x;
    }

    private float getRandomY(float offset) {
        float y = random.nextFloat(yMinSpawn, yMaxSpawn - offset);
        return y;
    }

    private void starWithPlayer() {
        if (Collider2D.OnCollison2D(star.getCollider2D(),
                Game.getInstance().getPlaying().getPlayer().getCollider2D())) {

            star.setPosition((float) getRandowX(star.getWidth()), (float) getRandomY(star.getHeight()));

            if (Game.getInstance().getPlaying().getScore().getScore() % 10 == 0) {
                spawnHeartStars(DICTANCE_HEART);
            }

        }
    }

    private void updateStar() {

        if (star != null) {
            star.setIsActive(true);
            star.update();
            starWithPlayer();
        }

    }

    public void updateStars() {
        if (!stars.isEmpty()) {
            for (Star star : stars) {
                if (star.getIsActive()) {
                    star.update();
                }
            }
        }
    }

    public void update() {
        updateStar();
        updateStars();
    }

    private void initHeartStars(float dictance) {

        for (int i = 0; i < STAR_COUNT; i++) {

            float adjustedX = 0;
            float adjustedY = 0;

            Star star = new Star(adjustedX, adjustedY, ADJUSTED_STAR_SIZE, ADJUSTED_STAR_SIZE);
            star.setIsActive(false);
            stars.add(star);
        }
    }

    private void spawnHeartStars(float dictance) {
        float radius = random.nextInt(5, 8);

        for (int i = 0; i < STAR_COUNT; i++) {
            double t = i * (2 * Math.PI / STAR_COUNT);

            float x = (float) (radius * 16 * Math.pow(Math.sin(t), 3));
            float y = (float) (radius
                    * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));

            float adjustedX = star.getPosition().x + dictance + x;
            float adjustedY = star.getPosition().y - y;

            stars.get(i).setPosition(adjustedX, adjustedY);
            stars.get(i).setIsActive(true);
        }
    }

    private void renderStar(Graphics2D g2d) {
        if (star != null) {
            star.render(g2d);
        }
    }

    private void renderStars(Graphics2D g2d) {
        if (!stars.isEmpty()) {
            for (Star star : stars) {
                if (star.getIsActive()) {
                    star.render(g2d);
                }
            }
        }
    }

    public void render(Graphics2D g2d) {
        renderStar(g2d);
        renderStars(g2d);
    }

    public void resetStars() {
        for (Star star : stars) {
            star.setActive(false);
        }
    }
}
