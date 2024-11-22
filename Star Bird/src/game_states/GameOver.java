package game_states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import untilz.LoadSave;

public class GameOver implements StateMethods {

    private BufferedImage background;

    private BufferedImage currentSprite;
    private BufferedImage repeatDefault;
    private BufferedImage repeatHover;

    private int repeatWidth = 100;
    private int repeatHeight = 100;

    private int posX;
    private int posY;
    private boolean isMousePressed = false;
    private boolean isMouseOver = false;

    private Font gameOverFont;
    private Font highScoreFont;

    public GameOver() {
        background = LoadSave.loadImage(LoadSave.BACKGROUND_GAME_OVER);

        posX = (int) (Game.getInstance().GAME_WIDTH / 2) - repeatWidth / 2;
        posY = (int) (Game.getInstance().GAME_HEIGHT * 0.6);
        loadAnimations();
        currentSprite = repeatDefault;
        gameOverFont = new Font("Arial", Font.BOLD, 50);
        highScoreFont = new Font("Arial", Font.BOLD, 25);
    }

    private void loadAnimations() {
        repeatDefault = LoadSave.loadImage(LoadSave.BUTTON_REPEAT_DEFAULT);
        repeatHover = LoadSave.loadImage(LoadSave.BUTTON_REPEAT_HOVER);
    }

    private boolean isMouseInButton(int x, int y) {
        return x >= posX && x <= posX + repeatWidth &&
                y >= posY && y <= posY + repeatHeight;
    }

    private void setAnimation() {
        if (isMousePressed) {
            currentSprite = repeatHover;
        } else if (isMouseOver) {
            currentSprite = repeatHover;
        } else {
            currentSprite = repeatDefault;
        }
    }

    private void saveHighScore() {
        LoadSave.saveHighScore(Game.getInstance().getPlaying().getScore().getHighScore());
    }

    @Override
    public void update() {
        setAnimation();
        saveHighScore();
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.drawImage(background, 0, 0, Game.getInstance().GAME_WIDTH, Game.getInstance().GAME_HEIGHT, null);

        g2d.setFont(gameOverFont);
        g2d.setColor(Color.WHITE);

        String gameOverText = "Game Over";
        int textWidth = g2d.getFontMetrics().stringWidth(gameOverText);
        int textX = (Game.getInstance().GAME_WIDTH - textWidth) / 2;
        int textY = (int) (Game.getInstance().GAME_HEIGHT * 0.4);

        g2d.drawString(gameOverText, textX, textY);

        g2d.setFont(highScoreFont);
        g2d.setColor(Color.WHITE);
        String highScore = "Score: " + Game.getInstance().getPlaying().getScore().getScore();
        int textHighScoreWidth = g2d.getFontMetrics().stringWidth(highScore);
        int textHighScoreX = (Game.getInstance().GAME_WIDTH - textHighScoreWidth) / 2;
        int textHighScoreY = (int) (Game.getInstance().GAME_HEIGHT * 0.5);

        g2d.drawString(highScore, textHighScoreX, textHighScoreY);

        g2d.drawImage(currentSprite, posX, posY, repeatWidth, repeatHeight, null);
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

    }
}