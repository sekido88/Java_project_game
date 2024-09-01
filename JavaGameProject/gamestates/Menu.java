package JavaGameProject.gamestates;

import JavaGameProject.main.Game;
import JavaGameProject.ui.MenuButton;
import JavaGameProject.utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {

    private MenuButton[] menuButtons;
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private int centerX = Game.GAME_WIDTH / 2;
    private int startY = (int) (Game.GAME_HEIGHT / 10 * Game.SCALE);
    private int buttonYSpacing = (int) (120 * Game.SCALE);

    public Menu(Game game) {
        super(game);
        initMenuBackground();
        initButtons();
    }

    private void loadBackroung() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
    }

    private void initMenuBackground() {
        loadBackroung();
        menuX = (int) ((centerX - (backgroundImg.getWidth() * Game.SCALE) / 2));
        menuY = (int) (startY - 50);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE + buttonYSpacing + 60);
    }

    private void initButtons() {
        menuButtons = new MenuButton[] {
                new MenuButton(centerX, startY + (int) (100 * Game.SCALE), 0, Gamestate.PLAYING),
                new MenuButton(centerX, startY + buttonYSpacing + (int) (100 * Game.SCALE), 1, Gamestate.OPTIONS),
                new MenuButton(centerX, startY + 2 * buttonYSpacing + (int) (100 * Game.SCALE), 2, Gamestate.QUIT)
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
        g2d.drawString("MENU", Game.GAME_WIDTH / 2, 50);

        g2d.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
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
