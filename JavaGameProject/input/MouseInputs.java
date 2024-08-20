package JavaGameProject.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener;
/*
    cho phép người dung tương tác với chuột
    - Các hành động của chuột click,press,release,mouseEntered,mouseExited ;
    - Kéo thả , drag ....
*/

import JavaGameProject.main.GamePanel;


public class MouseInputs implements MouseListener, MouseMotionListener {
    
    private GamePanel gamePanel;
    private boolean checkMouseClicked = false;
    public MouseInputs (GamePanel gamePanel) {
       this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       
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
       
    }
}
