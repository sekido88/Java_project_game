package JavaGameProject.gamestates;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import JavaGameProject.entites.Player;
import JavaGameProject.levels.LevelManager;
import JavaGameProject.main.Game;
import JavaGameProject.main.GamePanel;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private GamePanel gamePanel;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        newGamePanel();
        newLevelMangager();
        newPlayer();
  
    }

    private void newGamePanel() {
        gamePanel = game.getGamePanel();
    }

    private void newPlayer() {
        player = new Player(100, 300, 60, 100);
        if (gamePanel != null) {
            player.setAnimationTick(gamePanel.getAniSpeed());
        }
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    private void newLevelMangager() {
        levelManager = new LevelManager(game);
    }

    @Override
    public void update() {
        if (gamePanel != null && player != null) {
            player.update();
            gamePanel.repaint();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        player.render(g2d);
        levelManager.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            player.setAttacking(true);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
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
