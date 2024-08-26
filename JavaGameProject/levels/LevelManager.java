package JavaGameProject.levels;

import JavaGameProject.main.Game;
import JavaGameProject.utilz.LoadSave;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        this.levelOne = new Level(LoadSave.GetLevelData());

        importOutsideSprites();
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i * 12 + j;
                levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);

            }
        }
    }

    public void draw(Graphics2D g2d) {

        for (int i = 0; i < game.TILE_IN_HEIGHT; i++) {
            for (int j = 0; j < game.TILE_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i);
                g2d.drawImage(levelSprite[index], game.TILES_SIZE * j, game.TILES_SIZE * i, game.TILES_SIZE,
                        game.TILES_SIZE, null);
            }
        }
    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
