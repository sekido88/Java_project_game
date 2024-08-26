package JavaGameProject.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import JavaGameProject.entites.Player;
import JavaGameProject.levels.LevelManager;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public final static int TILE_DEFAULT_SIZE = 48;
    public final static float SCALE = 1.0f;
    public final static int TILE_IN_WIDTH = 26;
    public final static int TILE_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILE_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILE_IN_HEIGHT;
    public final static float GRAVITY = 9.8f * Game.SCALE;

    public Game() {

        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel); // Phải có gameWindow trước sau đó mới focus được
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        newLevelMangager();
        newPlayer();
    }

    private void newPlayer() {
        player = new Player(100, 300, 60, 100);
        if (gamePanel != null) {
            player.setAnimationTick(gamePanel.getAniSpeed());
        }
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    private void newLevelMangager() {
        levelManager = new LevelManager(this);
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start(); // chạy phương thức run của Class g
    }

    public void update() {
        if (gamePanel != null && player != null) {
            player.update();
            gamePanel.repaint();
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player.render(g2d);
        levelManager.draw(g2d);
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
