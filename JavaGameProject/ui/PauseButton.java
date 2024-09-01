package JavaGameProject.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

import JavaGameProject.main.Game;

public class PauseButton {
    public static final int SOUND_SIZE_DEFAULT = 42;
    public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);

    protected int x, y, width, height;
    protected Rectangle bounds;

    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    void update() {

    }

    void draw(Graphics2D g2d) {
   
    }
}
