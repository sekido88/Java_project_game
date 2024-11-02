package entity.obstacle;

import main.Game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import entity.item.Star;
import java.util.List;
import java.util.Iterator;

public class ObstacleSpawner {

    private Random random;
    private List<Obstacle> obstacles;
    private ObstacleType[] obstacleTypes;

    private float xMinSpawn;
    private float xMaxSpawn;
    private float yMinSpawn;
    private float yMaxSpawn;

    private final int MAX_ACTIVE_OBSTACLES = 10;
    private long SPAWN_INTERVAL = 1500;
    private long lastSpawnTime;

    public ObstacleSpawner() {
        random = new Random();
        obstacles = new ArrayList<>();
        obstacleTypes = ObstacleType.values();
        lastSpawnTime = System.currentTimeMillis();

        setSpaceBounds();
    }

    private void setSpaceBounds() {
        xMinSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.1);
        xMaxSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.9);
        yMinSpawn = (float) (-Game.getInstance().GAME_HEIGHT * 0.7);
        yMaxSpawn = (float) (0);
    }

    private void spawnObstacle() {

        if (obstacles.size() >= MAX_ACTIVE_OBSTACLES) {
            return;
        }

        ObstacleType obstacleType = obstacleTypes[random.nextInt(obstacleTypes.length)];
        Obstacle obstacle = createObstacle(obstacleType);
        obstacles.add(obstacle);
    }

    private Obstacle createObstacle(ObstacleType obstacleType) {
        float x = getRandomX(obstacleType.getWidth());
        float y = getRandomY(obstacleType.getHeight());
        return new Obstacle(x, y, obstacleType);
    }

    private float getRandomX(int obstacleWidth) {
        return random.nextFloat(xMinSpawn, xMaxSpawn - obstacleWidth);
    }

    private float getRandomY(int obstacleHeight) {
        return random.nextFloat(yMinSpawn, yMaxSpawn + obstacleHeight);
    }

    private void spawnObstacles() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL) {
            if (Game.getInstance().getPlaying().getScore().getScore() % 10 == 0) {
                SPAWN_INTERVAL -= 0.01;
            }

            if (obstacles.size() < MAX_ACTIVE_OBSTACLES) {
                spawnObstacle();
            }
            lastSpawnTime = currentTime;
        }
    }

    private void udpateObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();

            if (obstacle.getPosition().y > Game.getInstance().GAME_HEIGHT) {
                iterator.remove();
                continue;
            }

            obstacle.update();
        }
    }

    public void update() {
        spawnObstacles();
        udpateObstacles();
    }

    public void render(Graphics2D g2d) {
        if(!obstacles.isEmpty()) {
            for (Obstacle obstacle : obstacles) {
                obstacle.render(g2d);
            }
        }
    }

    public void reset() {
        obstacles.clear();
        lastSpawnTime = System.currentTimeMillis();
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

}