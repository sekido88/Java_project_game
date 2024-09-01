package JavaGameProject.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import JavaGameProject.main.Game;
import JavaGameProject.utilz.LoadSave;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private int soundButtonSpace = 10;

    public PauseOverlay() {
        loadBackround();
        createSoundButtons();
    }

    private void createSoundButtons() {
        int soundX = bgX + (int) ((background.getWidth() - (background.getWidth() * 0.4f)) * Game.SCALE);
        int musicY = bgY + (int) ((background.getHeight() - (background.getHeight() * 0.7f)) * Game.SCALE);
        int sfxY = soundButtonSpace + bgY
                + (int) ((background.getHeight() - (background.getHeight() * 0.6f)) * Game.SCALE);
        ;

        musicButton = new SoundButton(soundX, musicY, SoundButton.SOUND_SIZE, SoundButton.SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SoundButton.SOUND_SIZE, SoundButton.SOUND_SIZE);
    }

    private void loadBackround() {
        background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (background.getWidth() * Game.SCALE);
        bgH = (int) (background.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = 100;
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, bgX, bgY, bgW, bgH, null);

        musicButton.draw(g2d);
        sfxButton.draw(g2d);
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        if (musicButton.isMouseOver(e.getX(), e.getY())) {
            musicButton.setMouseOver(true);
        }
        if (sfxButton.isMouseOver(e.getX(), e.getY())) {
            sfxButton.setMouseOver(true);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (musicButton.isMouseOver(e.getX(), e.getY())) {
            musicButton.setMousePressed(true);
        } else if (sfxButton.isMouseOver(e.getX(), e.getY())) {
            sfxButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (musicButton.isMouseOver(e.getX(), e.getY())) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        if (sfxButton.isMouseOver(e.getX(), e.getY())) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }
        musicButton.resetBools();
        sfxButton.resetBools();
    }

}
