package JavaGameProject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import JavaGameProject.main.Game;
import JavaGameProject.main.GamePanel;

public class KeyBoardInputs implements KeyListener{

    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }                             

    public void keyTyped(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // KEYEVENT LA MOT DOI TUONG TRONG LOP EVENT CO THE XU LY
                this.gamePanel.player.resetDirBooleans();    
                this.gamePanel.player.setUp(true);

                break;
            case KeyEvent.VK_A:
                this.gamePanel.player.resetDirBooleans();    
                this.gamePanel.player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                this.gamePanel.player.resetDirBooleans();    
                this.gamePanel.player.setDown(true);
                break;
            case KeyEvent.VK_D:
                this.gamePanel.player.resetDirBooleans();    
                this.gamePanel.player.setRight(true);
                break;
            default:
                break;
        }
    }
}
