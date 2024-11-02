package JavaGameProject.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import JavaGameProject.entites.Player;
import JavaGameProject.levels.LevelManager;
import JavaGameProject.main.Game;
import JavaGameProject.main.GamePanel;
import JavaGameProject.ui.PauseOverlay;
import JavaGameProject.utilz.LoadSave;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private GamePanel gamePanel;
    private PauseOverlay pauseOverlay;

    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.9 * Game.GAME_WIDTH);
    private int lvlTileWide = LoadSave.GetLevelData()[0].length;
    private int maxTileOffset = lvlTileWide - Game.TILE_IN_WIDTH;
    private int maxLvlOffsetX = maxTileOffset * Game.TILES_SIZE;

    private boolean paused = true;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        newGamePanel();
        newLevelMangager();
        newPlayer();
        newPauseOverlay();
    }

    private void newGamePanel() {
        gamePanel = game.getGamePanel();
    }

    private void newPlayer() {
        player = new Player(100, 300, (int) (60 * Game.SCALE), (int) (100 * Game.SCALE));
        if (gamePanel != null) {
            player.setAnimationTick(gamePanel.getAniSpeed());
        }
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    private void newLevelMangager() {
        levelManager = new LevelManager(game);
    }

    private void newPauseOverlay() {
        pauseOverlay = new PauseOverlay();
    }

    @Override
    public void update() {
        if (gamePanel != null && player != null) {
            player.update();
            checkCloseToBorder();
            gamePanel.repaint();
        }
        pauseOverlay.update();
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder) {
            xLvlOffset += diff - leftBorder;
        }

        if(xLvlOffset > maxLvlOffsetX) 
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0) 
            xLvlOffset = 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.render(g2d, xLvlOffset);
        levelManager.draw(g2d);
        if(paused)
            pauseOverlay.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_ENTER:
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJump(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
        }
    }

}
