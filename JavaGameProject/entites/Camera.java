package JavaGameProject.entites;

import JavaGameProject.main.Game;

public class Camera {
    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        // Center the camera on the player
        this.x = player.getX() - Game.GAME_WIDTH / 2;
        this.y = player.getY() - Game.GAME_HEIGHT / 2;

        // Ensure the camera doesn't go out of bounds (assuming the game world has bounds)
        // You can add conditions here to limit x and y
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
