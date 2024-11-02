package untilz;

import javax.imageio.ImageIO;

import entity.player.Player.PlayerAction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class LoadSave {
    // player
    public static final String PLAYER_ANIMATIONS = "res/player/duck1/";

    // background
    public static final String BACKGROUND_LAYER_1 = "res/background/layer_1.png";
    public static final String BACKGROUND_LAYER_2 = "res/background/layer_2.png";
    public static final String BACKGROUND_LAYER_3 = "res/background/layer_3.png";

    public static final String BACKGROUND_GAME_MENU = "res/background/background_game_menu.png";

    // item
    public static final String STAR = "res/item/star.png";
    public static final String SHOOTING_STAR = "res/item/shotting_star_2.png";

    // obstacle
    public static final String METEOR = "res/obstacle/meteor.png";
    public static final String FLAMING_METEOR = "res/obstacle/flaming_meteor.png";
   
    // audio
    public static final String BGM = "res/audio/game_loop.wav";
    public static final String SFX_TAP = "res/audio/tap.wav";
    public static final String SFX_COIN_TAP = "res/audio/coin_tap.wav";
    public static final String SFX_GOT_HIT = "res/audio/got_hit.wav";
    public static final String SFX_GAME_OVER = "res/audio/game_over.wav";
    public static final String SFX_HAPPY_MOMMENT = "res/audio/happy_moment.wav";

    // ui
    public static final String BUTTON_REPEAT_DEFAULT = "res/ui/repeat/default.png";
    public static final String BUTTON_REPEAT_HOVER = "res/ui/repeat/hover.png";

    public static final String BUTTON_PLAY_DEFAULT = "res/ui/play/default.png";
    public static final String BUTTON_PLAY_HOVER = "res/ui/play/hover.png";

    // data
    public static final String DATA_HIGH_SCORE = "res/data/high_score.txt";

    public static int loadHighScore() {
        try {
            File file = new File(DATA_HIGH_SCORE);
            if(!file.exists()) {
                return 0;
            }

            Scanner scanner = new Scanner(file);
            int highScore = scanner.hasNextInt() ? scanner.nextInt() : 0;
            scanner.close();

            return highScore;

        } catch(IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveHighScore(int highScore) {
        try {
            FileWriter writer = new FileWriter(DATA_HIGH_SCORE);
            writer.write(String.valueOf(highScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File does not exist: " + file.getAbsolutePath());
                return null;
            }

            img = ImageIO.read(file);

            if (img == null) {
                System.out.println("Failed to load image from existing file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image from path: " + path);
        }
        return img;
    }

    public static List<BufferedImage[]> getSpriteAnimations(String fileName, PlayerAction[] actions) {
        List<BufferedImage[]> animations = new ArrayList<>();

        for (PlayerAction action : actions) {

            String path = fileName + action.name().toLowerCase();
            BufferedImage[] actionFrames = new BufferedImage[action.getSpriteAmount()];

            for (int i = 0; i < action.getSpriteAmount(); i++) {
                BufferedImage img = loadImage(path + "_" + (i + 1) + ".png");
                actionFrames[i] = img;
            }

            animations.add(actionFrames);

        }
        return animations;
    }

}