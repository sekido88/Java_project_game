package game_states;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public interface StateMethods {
    public void update();
    
    public void render(Graphics2D g2d);
    
    public void mouseClicked(MouseEvent e);
    
    public void mousePressed(MouseEvent e);
    
    public void mouseReleased(MouseEvent e);
    
    public void mouseMoved(MouseEvent e) ;
    
    public void mouseExited(MouseEvent e) ;
    
}