package entity.item;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import main.Game;

public class ItemSpawner {
    private List<Item> items;
    private Random random;
    private float xMinSpawn;
    private float xMaxSpawn;
    private float yMinSpawn;
    private float yMaxSpawn;

    private final int MAX_ACTIVE_ITEMS = 5;
    private long SPAWN_INTERVAL_SHIELD = 10000; 
    private long SPAWN_INTERVAL_SHOOTING_STAR = 15000;

    private long lastShieldSpawnTime;
private long lastShootingStarSpawnTime;

    public ItemSpawner() {
        items = new ArrayList<>();
        random = new Random();
        lastShieldSpawnTime = System.currentTimeMillis();
        lastShootingStarSpawnTime = System.currentTimeMillis();
        setSpaceBounds();
    }

    private void setSpaceBounds() {
        xMinSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.1);
        xMaxSpawn = (float) (Game.getInstance().GAME_WIDTH * 0.9);
        yMinSpawn = (float) (-Game.getInstance().GAME_HEIGHT * 0.7);
        yMaxSpawn = (float) (0);
    }

    private void spawnShield() {
        if (items.size() >= MAX_ACTIVE_ITEMS)
        return;
        float x = random.nextFloat(xMinSpawn, xMaxSpawn - ItemType.SHIELD.getWidth());
        float y = random.nextFloat(yMinSpawn, yMaxSpawn);
        items.add(new Shield(x, y));
    }

    private void spawnShootingStar() {
        if (items.size() >= MAX_ACTIVE_ITEMS)
        return;
        float x = random.nextFloat(xMinSpawn, xMaxSpawn - ItemType.SHOOTING_STAR.getWidth());
        float y = random.nextFloat(yMinSpawn, yMaxSpawn);
        items.add(new ShootingStar(x, y));
    }

    private void updateItems() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();

            if (item.getPosition().y > Game.getInstance().GAME_HEIGHT
                    || (!item.isActive() && !item.getIsEffectActive())) {
                iterator.remove();
                continue;
            }

            item.update();

        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastShieldSpawnTime >= SPAWN_INTERVAL_SHIELD) {
            spawnShield();
            lastShieldSpawnTime = currentTime;
        }
    
        if (currentTime - lastShootingStarSpawnTime >= SPAWN_INTERVAL_SHOOTING_STAR) {
            spawnShootingStar();
            lastShootingStarSpawnTime = currentTime;
        }
    
        updateItems();
    }

    public void render(Graphics2D g2d) {
        if (!items.isEmpty()) {
            for (Item item : items) {
                item.render(g2d);
            }
        }
    }

    public void reset() {
        items.clear();
        lastShieldSpawnTime = System.currentTimeMillis();
        lastShootingStarSpawnTime = System.currentTimeMillis();
    }
}