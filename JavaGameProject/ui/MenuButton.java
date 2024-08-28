package JavaGameProject.ui;

import JavaGameProject.gamestates.Gamestate;
import JavaGameProject.main.Game;
import JavaGameProject.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private static final int B_WIDTH_DEFAULT = 140;
    private static final int B_HEIGHT_DEFAULT = 100;
    private static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
    private static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);

    private int xPos, yPos;
    private int aniIndex;
    private int rowIndex;
    private Gamestate state;
    private BufferedImage[][] imgs;
    private Rectangle bounds;
    private boolean mouseOver;
    private boolean mousePressed;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        this.rowIndex = rowIndex;
        this.aniIndex = 0;
        loadImages();
        initBounds();
    }

    private void loadImages() {
        imgs = new BufferedImage[3][3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        int imgWidth = temp.getWidth() / 3;
        int imgHeight = temp.getHeight() / 3;

        for (int i = 0; i < imgs.length; i++) {
            for (int j = 0; j < imgs[i].length; j++) {
                imgs[i][j] = temp.getSubimage(j * imgWidth, i * imgHeight, imgWidth, imgHeight);
            }
        }
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - B_WIDTH / 2, yPos, B_WIDTH, B_HEIGHT);
    }

    public void update() {
        aniIndex = 0;
        if (mouseOver) {
            aniIndex = 1;
        }
        if (mousePressed) {
            aniIndex = 2;
        }
    }

    public void draw(Graphics2D g2d) {
        drawStateImages(g2d);
    }

    private void drawStateImages(Graphics2D g2d) {
        g2d.drawImage(imgs[rowIndex][aniIndex], xPos - B_WIDTH / 2, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public boolean isMouseOver(float x, float y) {
        return bounds.contains(x, y);
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
        if (mouseOver) {
            aniIndex = 1;
        } else if (!mousePressed) {
            aniIndex = 0;
        }
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
        if (mousePressed) {
            aniIndex = 2;
        } else if (!mouseOver) {
            aniIndex = 0;
        }
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public Gamestate getGamestateInButton() {
        return state;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public void reset() {
        mousePressed = false;
        mouseOver = false;
    }

}
