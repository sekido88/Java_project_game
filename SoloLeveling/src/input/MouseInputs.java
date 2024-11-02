package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import game_states.Gamestate;

import main.Game;

public class MouseInputs implements MouseListener, MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                Game.getInstance().getMenu().mouseMoved(e);
            case PLAYING:
                Game.getInstance().getPlaying().mouseMoved(e);
                break;
            case GAME_OVER:
                Game.getInstance().getGameOver().mouseMoved(e);
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                Game.getInstance().getMenu().mouseClicked(e);
            case PLAYING:
                Game.getInstance().getPlaying().mouseClicked(e);
                break;
            case GAME_OVER:
                Game.getInstance().getGameOver().mouseClicked(e);
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                Game.getInstance().getMenu().mousePressed(e);
            case PLAYING:
                Game.getInstance().getPlaying().mousePressed(e);
                break;
            case GAME_OVER:
                Game.getInstance().getGameOver().mousePressed(e);
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
 
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
