package entity.score;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

import main.Game;
import untilz.LoadSave;

public class Score {
    private int score;
    private int highScore;
    private static final int SCORE_X = (int) (Game.getInstance().GAME_WIDTH / 2);
    private static final int SCORE_Y = (int) (Game.getInstance().GAME_HEIGHT * 0.1f);

    private static final int HIGH_SCORE_X = (int) (Game.getInstance().GAME_WIDTH * 0.7f);
    private static final int HIGH_SCORE_Y = (int) (Game.getInstance().GAME_HEIGHT * 0.05f);

    private Font scoreFont;
    private Font highScoreFont;

    public Score() {
        score = 0;
        highScore = LoadSave.loadHighScore();
        scoreFont = new Font("Arial", Font.BOLD, 25);
        highScoreFont = new Font("Arial", Font.BOLD, 15);
    }

    public void addScore(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void render(Graphics2D g2d) {
        g2d.setFont(scoreFont);
        g2d.setColor(Color.YELLOW);

        g2d.drawString("" + score, SCORE_X, SCORE_Y);

        g2d.setFont(highScoreFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString("High Score: " + highScore, HIGH_SCORE_X, HIGH_SCORE_Y);
    }
}