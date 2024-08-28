package JavaGameProject.gamestates;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public interface StateMethods {
    public void update();
    
    public void draw(Graphics2D g2d);
    
    public void mouseClicked(MouseEvent e);
    
    public void mousePressed(MouseEvent e);
    
    public void mouseMoved(MouseEvent e);
    
    public void keyPressed(KeyEvent e);
    
    public void keyReleased(KeyEvent e);
    
}
