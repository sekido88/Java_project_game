package JavaGameProject.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import JavaGameProject.utilz.LoadSave;

public class SoundButton extends PauseButton{

    private BufferedImage[][] soundImgs;
    private int rowIndex,colIndex;
    private boolean mouseOver,mousePressed;
    private boolean muted;

    public SoundButton(int x, int y ,int width, int height) {
        super(x, y, width, height);
        loadSoundImgs();
    }
    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int i = 0; i < soundImgs.length; i++) {
            for (int j = 0; j < soundImgs[i].length; j++) {
                soundImgs[i][j] = temp.getSubimage(j * SOUND_SIZE_DEFAULT, i * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    void draw(Graphics2D g2d) {
        g2d.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
    }

    void update() {
        updateAniIndex();
    }

    private void updateAniIndex() {
        if(muted) {
            rowIndex = 1;
        } else {
            rowIndex = 0;
        }
        colIndex = 0;
        if(mouseOver) {
            colIndex = 1;
        }
        if(mousePressed) {
            colIndex = 2;
        }
    }

    public boolean isMouseOver(float x,float y) {
        return bounds.contains(x, y);
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public boolean isMousePressed() {
        return mousePressed;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public boolean isMuted() {
        return muted;
    }
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
    
}
