package JavaGameProject.utilz;

import JavaGameProject.main.Game;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class HelpMothods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {

        if (!IsSolid(x, y, levelData) &&
                !IsSolid(x + width, y, levelData) &&
                !IsSolid(x, y + height, levelData) &&
                !IsSolid(x + width, y + height, levelData)) {
            return true;
        }
        return false;
    }

    public static boolean IsSolid(float x, float y, int[][] levelData) {

        if (x < 0 || x >= Game.GAME_WIDTH || y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        int xIndex = (int) Math.floor(x / Game.TILES_SIZE);
        int yIndex = (int) Math.floor(y / Game.TILES_SIZE);

        int value = levelData[yIndex][xIndex];

        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }
        return false;
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffSet = (int) (Game.TILES_SIZE - hitBox.width);
            return tileXPos + tileXPos - 1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Falling, chạm sàn
            int tileYPos = (currentTile + 1) * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
            return tileYPos - yOffset - 1;
        } else {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData) {
        if (!IsSolid(hitBox.x, hitBox.y + hitBox.height + 4, lvlData)) {
            if (!IsSolid(hitBox.x + hitBox.width, hitBox.y, lvlData)) {
                return false;
            }
        }
        return true;
    }
}
