package game_states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entity.item.ItemSpawner;
import entity.item.ItemState;
import entity.obstacle.ObstacleSpawner;
import entity.player.Player;
import entity.score.Score;
import entity.star.StarSpawner;
import main.Game;
import untilz.LoadSave;

public class Playing implements StateMethods {

    private BufferedImage background;
    // player
    private int playerPosX = 480 / 2;
    private int playerPosY = 10;
    private int playerWidth = 50;
    private int playerHeight = 50;

    private StarSpawner starSpawner;
    private ObstacleSpawner obstacleSpawner;
    private ItemSpawner itemSpawner;
    private Score score;
    private Player player;

    private long currentTime;
    private long lastTime;
    private final long delayTime = 3000;

    private void initItemSpawner() {
        this.itemSpawner = new ItemSpawner();
    }

    private void initPlayer() {
        this.player = new Player(playerPosX, playerPosY, playerWidth, playerHeight);
    }

    private void initScore() {
        this.score = new Score();
    }

    private void initStarSpawner() {
        this.starSpawner = new StarSpawner();
    }

    private void initObstacleSpawner() {
        this.obstacleSpawner = new ObstacleSpawner();
    }

    private void loadImageBackground() {
        background = LoadSave.loadImage(LoadSave.BACKGROUND_GAME_PLAYING);
    }


    private void initClass() {
        initScore();
        initPlayer();
        initStarSpawner();
        initItemSpawner();
        initObstacleSpawner();
    }

    public Playing() {
        Game.getInstance().resetBolean();
        loadImageBackground();
        initClass();
    }

    private void renderBackground(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, Game.getInstance().GAME_WIDTH, Game.getInstance().GAME_HEIGHT, null);
    }

    private void checkGameIsOver() {
        if (Game.getInstance().getIsGameOver()) {
            currentTime = System.currentTimeMillis();

            if (currentTime - lastTime >= delayTime) {
                Game.getInstance().getAudioPlayer().play("game_over", false);
                Game.getInstance().setIsGameOver(false);
                Gamestate.state = Gamestate.GAME_OVER;
            }
        } else {

            lastTime = System.currentTimeMillis();
        }

    }

    @Override
    public void update() {
        checkGameIsOver();

        itemSpawner.update();
        player.update();
        Game.getInstance().getEffectManager().update();

        if (!ItemState.SHOOTING_STAR.isEffect()) {
            starSpawner.update();
            obstacleSpawner.update();
        } else {
            starSpawner.resetStars();
            obstacleSpawner.reset();
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        renderBackground(g2d);

      
        itemSpawner.render(g2d);
        player.render(g2d);
        

        if (!ItemState.SHOOTING_STAR.isEffect()) {
            starSpawner.render(g2d);
            obstacleSpawner.render(g2d);
        }
        Game.getInstance().getEffectManager().render(g2d);
        score.render(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.player.mouseClickForce();
            Game.getInstance().getAudioPlayer().play("tap", false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public StarSpawner getStarSpawner() {
        return starSpawner;
    }

    public ObstacleSpawner getObstacleSpawner() {
        return obstacleSpawner;
    }

    public Score getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}
