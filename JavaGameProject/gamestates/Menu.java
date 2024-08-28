package JavaGameProject.gamestates;

import JavaGameProject.main.Game;
import JavaGameProject.ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {

    private MenuButton[] menuButtons;
    private static final int BUTTON_SPACING = 10; // Khoảng cách giữa các nút

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int centerX = Game.GAME_WIDTH / 2;
        int startY = 50;
        int buttonYSpacing = 120; // Khoảng cách giữa các nút theo chiều dọc

        menuButtons = new MenuButton[] {
                new MenuButton(centerX, startY, 0, Gamestate.PLAYING),
                new MenuButton(centerX, startY + buttonYSpacing, 1, Gamestate.OPTIONS),
                new MenuButton(centerX, startY + 2 * buttonYSpacing, 2, Gamestate.QUIT)
        };
    }

    @Override
    public void update() {

        for (MenuButton button : menuButtons) {
            button.update();
        }

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawString("MENU", Game.GAME_WIDTH / 2 - 20, 50);

        for (MenuButton button : menuButtons) {
            button.draw(g2d);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (MenuButton button : menuButtons) {
            button.setMousePressed(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (MenuButton button : menuButtons) {
            if (button.isMouseOver(e.getX(), e.getY())) {
                if (button.getGamestateInButton().toString() != null) {
                    button.setStateButton(button.getGamestateInButton().toString());
                    button.setMousePressed(true);
                    button.applyGamestate();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : menuButtons) {
            button.setMouseOver(false);
        }
        for (MenuButton button : menuButtons) {
            if (button.isMouseOver(e.getX(), e.getY())) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : menuButtons) {
            if (button.isMouseOver(e.getX(), e.getY())) {
                button.setMousePressed(true);
            }
        }
    }

}
