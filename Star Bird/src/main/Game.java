package main;

import java.awt.Graphics2D;

import audio.AudioPlayer;
import entity.efffect.EffectManager;
import game_states.GameOver;
import game_states.Gamestate;
import game_states.Menu;
import game_states.Playing;
import unity.Vector2D;

import entity.efffect.EffectType;
import entity.efffect.Effect;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private AudioPlayer audioPlayer = AudioPlayer.getInstance();
    private EffectManager effectManager = EffectManager.getInstance();

    private Menu menu;
    private Playing playing;
    private GameOver gameOver;

    private boolean isGameOver = false;

    private final int FPS_SET = 60;
    private final int UPS_SET = 200;
    public final double DELTA_TIME = 1.0 / FPS_SET;
    public final int GAME_WIDTH = 480;
    public final int GAME_HEIGHT = 720;
    public final Vector2D GRAVITY = new Vector2D(0, 18.81f);

    public static Game instance = null;

    private void initGameState() {
        playing = new Playing();
        menu = new Menu();
        gameOver = new GameOver();
    }

    private void initAudio() {
        audioPlayer.play("game_loop", true);
    }

    private void initGamePanel() {
        this.gamePanel = new GamePanel();
    }

    private void initGameWindow() {
        this.gameWindow = new GameWindow(gamePanel);
    }

    private void initClass() {
        initAudio();
        initGameState();
        initGamePanel();
        initGameWindow();

    }

    public Game() {
        if (instance == null)
            instance = this;

        initClass();
        startGameLoop();

    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    private void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case GAME_OVER:
                gameOver.update();
            default:
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        long lastCheck = System.currentTimeMillis();
        long currentTime;

        while (true) {
            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void render(Graphics2D g2d) {
        if (this != null) {
            switch (Gamestate.state) {
                case MENU:
                    menu.render(g2d);
                    break;
                case PLAYING:
                    playing.render(g2d);
                    break;
                case GAME_OVER:
                    gameOver.render(g2d);
                    break;
                default:
                    break;
            }
        }
    }

    public Playing getPlaying() {
        return playing;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public Menu getMenu() {
        return menu;
    }

    public EffectManager getEffectManager() {
        return effectManager;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setPlaying(Playing playing) {
        this.playing = playing;
    }

    public void resetBolean() {
        this.isGameOver = false;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}