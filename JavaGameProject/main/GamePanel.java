package JavaGameProject.main;

// import static JavaGameProject.player.Player.PlayerConstants.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import JavaGameProject.input.KeyBoardInputs;
import JavaGameProject.input.MouseInputs;
import JavaGameProject.player.Player;

public class GamePanel extends JPanel {

    // Biến lưu trữ
    private MouseInputs mouseInputs;
    private int xDelta = 40, yDelta = 40;
    private int xSpeed = 5, ySpeed = 3;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Color> colors;
    private ArrayList<Point> velocities;
    private Random random;
    private int aniTick, aniIndex, aniSpeed = 10;
    public Player player;

    public GamePanel() {
        initializeInputs();
        setPanelSize();
        newPlayer();
    }

    // Khởi tạo các input
    private void newPlayer() {
        player = new Player(50, 50, 400, 600);
        player.setAnimationTick(aniSpeed);
    }

    private void initializeInputs() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Thiết lập kích thước panel
    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        player.render(g2d);
    }
}
