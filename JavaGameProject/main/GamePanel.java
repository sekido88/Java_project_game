package JavaGameProject.main;

import java.awt.*;
import javax.swing.JPanel;
import JavaGameProject.input.KeyBoardInputs;
import JavaGameProject.input.MouseInputs;

public class GamePanel extends JPanel {

    // Biến lưu trữ
    private MouseInputs mouseInputs;
    private int xDelta = 40, yDelta = 40;
    private int xSpeed = 5, ySpeed = 3;
    private int aniTick, aniIndex, aniSpeed = 10;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        initializeInputs();
        setPanelSize();

    }

    private void initializeInputs() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Thiết lập kích thước panel
    private void setPanelSize() {
        Dimension size = new Dimension(game.GAME_WIDTH,game.GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println(game.GAME_HEIGHT + "|||" +game.GAME_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        game.render(g);
    }

    public Game getGame() {
        return game;
    }

    public int getAniSpeed() {
        return aniSpeed;
    }
}
