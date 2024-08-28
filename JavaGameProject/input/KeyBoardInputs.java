package JavaGameProject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import JavaGameProject.gamestates.Gamestate;
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
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:  
                gamePanel.getGame().getPlaying().keyReleased(e);
                break; 
            default:
                break;    
       }
    }

    public void keyPressed(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:  
                gamePanel.getGame().getPlaying().keyPressed(e);
                break; 
            default:
                break;    
           }
    }

}
