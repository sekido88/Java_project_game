package main;

import javax.swing.JPanel;
import input.MouseInputs;

import java.awt.*;

public class GamePanel extends JPanel {
    private Graphics2D g2d;
    private MouseInputs mouseInputs;

    public GamePanel() {
        initScreen();
        initInput();
    }

    private void initInput() {
        mouseInputs = new MouseInputs();
        this.addMouseListener(mouseInputs);

    }

    public void initScreen() {
        Dimension size = new Dimension(Game.getInstance().GAME_WIDTH, Game.getInstance().GAME_HEIGHT);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        Game.getInstance().render(g2d);
        repaint();
    }
}
