package JavaGameProject.main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); // Phản ứng nhanh hơn với input từ người dùng
        startGameLoop();
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start(); // chạy phương thức run của Class g
    }

    @Override
    public void run() {
        // khoi tao = start trong unity
        double timePerFrame = 1000000000.0 / FPS_SET; // thoi gian để vẽ một frame
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastCheck = System.currentTimeMillis();

        // lap vo han
        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {

                gamePanel.player.update();
                gamePanel.repaint();

                lastFrame = System.nanoTime();
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println(frames);
                frames = 0;
            }

        }
    }
}
