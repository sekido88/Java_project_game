package JavaGameProject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import JavaGameProject.main.Game;
import JavaGameProject.main.GamePanel;

public class KeyBoardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.gamePanel.getGame().getPlayer().setJump(false);
                break;
            case KeyEvent.VK_A:
                this.gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
                this.gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_S:
                this.gamePanel.getGame().getPlayer().setDown(false);
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.gamePanel.getGame().getPlayer().setJump(true);
                break;
            case KeyEvent.VK_A:
    
                this.gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                this.gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                this.gamePanel.getGame().getPlayer().setRight(true);
                break;
            // case KeyEvent.VK_SPACE:
            // this.gamePanel.getGame().getPlayer().setJump(true);
            // break;
        }
    }

}
