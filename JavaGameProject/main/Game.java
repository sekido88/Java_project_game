package JavaGameProject.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import JavaGameProject.player.Player;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public Game() {

        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel); // Phải có gameWindow trước sau đó mới focus được
        gamePanel.requestFocus(); 

        
        startGameLoop();
    }

    private void initClasses() {
        newPlayer();
    }

    private void newPlayer() {
        player = new Player(50, 50, 400, 600);
        if (gamePanel != null) {
            player.setAnimationTick(gamePanel.getAniSpeed());
        }
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start(); // chạy phương thức run của Class g
    }

    public void update() {
        // System.out.println("dang duoc update");
        if (gamePanel != null && player != null ){
            player.update();
            gamePanel.repaint();
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player.render(g2d);
    }

    @Override
    public void run() {
        // khoi tao = start trong unity
        double timePerFrame = 1000000000.0 / FPS_SET; // thoi gian để vẽ một frame
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        long lastCheck = System.currentTimeMillis();
        long currentTime = System.nanoTime();

        // lap vo han
        while (true) {

            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1) {
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                update();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS" + frames + "  UPS" + updates);
                frames = 0;
                updates = 0;
            }

        }
    }

    public Game getGame() {
        return this;
    }

    public Player getPlayer() {
        return player;
    }
}
