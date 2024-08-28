package JavaGameProject.utilz;

import JavaGameProject.entites.Player.PlayerAction;
import JavaGameProject.main.Game;

import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class LoadSave {
    public static final String PLAYER_ANIMATIONS = "JavaGameProject/res/FreeKnight/";
    public static final String LEVEL_ATLAS = "JavaGameProject/res/Level/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "JavaGameProject/res/Level/level_one_data.png";
    public static final String MENU_BUTTONS = "JavaGameProject/res/MenuButton/button_atlas.png";

    public static BufferedImage[][] GetSpriteAnimations(String fileNameAni, PlayerAction[] actions) {
        BufferedImage[][] animations = new BufferedImage[PlayerAction.values().length][];

        for (PlayerAction action : PlayerAction.values()) {
            animations[action.ordinal()] = new BufferedImage[action.getSpriteAmount()];
            for (int i = 0; i < action.getSpriteAmount(); i++) {
                String fileName = fileNameAni + action.name() + " (" + (i + 1) + ").png";
                try {
                    animations[action.ordinal()][i] = ImageIO.read(new File(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error loading file: " + fileName);
                }
            }
        }
        return animations;
    }

    public static BufferedImage GetSpriteAtlas(String fileNameAtlas) {
        BufferedImage img = null;
        try {
            InputStream is = LoadSave.class.getResourceAsStream("/" + fileNameAtlas);
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                System.out.println("Resource not found: " + fileNameAtlas);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading atlas: " + fileNameAtlas);
        }
        return img;
    }

    public static int[][] GetLevelData() {
        int[][] levelData = new int[Game.TILE_IN_HEIGHT][Game.TILE_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                levelData[i][j] = value;
            }
        }
        // System.out.println("LoadThanhCong");
        return levelData;
    }
}