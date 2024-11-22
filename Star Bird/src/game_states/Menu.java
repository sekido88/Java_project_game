package game_states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import main.Game;
import untilz.LoadSave;

public class Menu implements StateMethods {

    private BufferedImage background;

    private BufferedImage currentSprite;
    private BufferedImage playDefault;
    private BufferedImage playHover;

    private int playWidth = 100;
    private int playHeight = 100;

    private int posX;
    private int posY;
    private boolean isMousePressed = false;
    private boolean isMouseOver = false;
    private Font gameMenuFont;

    public Menu() {
        background = LoadSave.loadImage(LoadSave.BACKGROUND_GAME_MENU);

        posX = (int) (Game.getInstance().GAME_WIDTH / 2) - playWidth / 2;
        posY = (int) (Game.getInstance().GAME_HEIGHT * 0.6);
        loadAnimations();
        currentSprite = playDefault;
        gameMenuFont = new Font("Arial", Font.BOLD, 40);
    }

    private void loadAnimations() {
        playDefault = LoadSave.loadImage(LoadSave.BUTTON_PLAY_DEFAULT);
        playHover = LoadSave.loadImage(LoadSave.BUTTON_PLAY_HOVER);
    }

    private boolean isMouseInButton(int x, int y) {
        return x >= posX && x <= posX + playWidth &&
                y >= posY && y <= posY + playHeight;
    }

    private void setAnimation() {
        if (isMousePressed) {
            currentSprite = playHover;
        } else if (isMouseOver) {
            currentSprite = playHover;
        } else {
            currentSprite = playDefault;
        }
    }

    @Override
    public void update() {
        setAnimation();
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.drawImage(background, 0, 0, Game.getInstance().GAME_WIDTH, Game.getInstance().GAME_HEIGHT, null);

        g2d.setFont(gameMenuFont);
        g2d.setColor(Color.WHITE);

        String gameOverText = "Star Bird";
        int textWidth = g2d.getFontMetrics().stringWidth(gameOverText);
        int textX = (Game.getInstance().GAME_WIDTH - textWidth) / 2;
        int textY = (int) (Game.getInstance().GAME_HEIGHT * 0.2);

        g2d.drawString(gameOverText, textX, textY);

        g2d.drawImage(currentSprite, posX, posY, playWidth, playHeight, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isMouseInButton(e.getX(), e.getY())) {
            Game.getInstance().setPlaying(new Playing());
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // pressed in reset game
        if (isMouseInButton(e.getX(), e.getY())) {
            isMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        isMouseOver = isMouseInButton(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isMouseOver = false;
    }

}
