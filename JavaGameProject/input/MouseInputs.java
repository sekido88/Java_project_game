package JavaGameProject.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener;
/*
    cho phép người dung tương tác với chuột
    - Các hành động của chuột click,press,release,mouseEntered,mouseExited ;
    - Kéo thả , drag ....
*/

import JavaGameProject.gamestates.Gamestate;
import JavaGameProject.main.GamePanel;


public class MouseInputs implements MouseListener, MouseMotionListener {
    
    private GamePanel gamePanel;
    private boolean checkMouseClicked = false;
    public MouseInputs (GamePanel gamePanel) {
       this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Gamestate.state) {
        case MENU:
            if( gamePanel.getGame().getMenu() != null) {
                gamePanel.getGame().getMenu().mouseClicked(e);
            }
            break;
        case PLAYING:  
            if( gamePanel.getGame().getPlaying() != null) {
                gamePanel.getGame().getPlaying().mouseClicked(e);
            }
            break; 
        default:
            break;    
       }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                if(gamePanel.getGame().getMenu() != null) {
                    gamePanel.getGame().getMenu().mousePressed(e);
                }
                break;
            case PLAYING:  
                if(gamePanel.getGame().getPlaying() != null) {
                    gamePanel.getGame().getPlaying().mousePressed(e);
                }
                break; 
            default:
                break;    
           }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    } 

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                if(gamePanel.getGame().getMenu() != null) {
                    gamePanel.getGame().getMenu().mouseMoved(e);
                }
                break;
            case PLAYING:  
                if(gamePanel.getGame().getPlaying() != null) {
                    gamePanel.getGame().getPlaying().mouseMoved(e);
                }
                break; 
            default:
                break;    
           }
    }
}
