package JavaGameProject.player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private float x, y;
    private int width, height;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private PlayerAction playerAction = PlayerAction.Idle;

    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    public enum PlayerAction {
        Idle(10), Run(10), Attack(10), Dead(10), Jump(10), JumpAttack(10);

        private final int spriteAmount;

        PlayerAction(int spriteAmount) {
            this.spriteAmount = spriteAmount;
        }

        public int getSpriteAmount() {
            return this.spriteAmount;
        }
    }

    public Player(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadAnimations();
    }

    private void loadAnimations() {
        animations = new BufferedImage[PlayerAction.values().length][]; // lấy tất cả hành động của player và sau đó lấy
                                                                        // số lượng
        for (PlayerAction action : PlayerAction.values()) {
            animations[action.ordinal()] = new BufferedImage[action.getSpriteAmount()];
            for (int i = 0; i < action.getSpriteAmount(); i++) {
                String fileName = "JavaGameProject/res/FreeKnight/png/" + action.name() + " (" + (i + 1) + ").png";

                try {
                    animations[action.ordinal()][i] = ImageIO.read(new File(fileName));
                } catch (IOException e) {
                    e.printStackTrace(); // in ra lỗi
                    System.out.println(fileName);
                }
            }
        }
    }

    public void setAnimationTick(int anispeed) {
        this.aniSpeed = anispeed;
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= playerAction.getSpriteAmount()) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        PlayerAction startAni = playerAction;

        if (moving) {
            playerAction = PlayerAction.Run;
        } else {
            playerAction = PlayerAction.Idle;
        }

        if (attacking) {
            playerAction = PlayerAction.Attack;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (!up && down) {
            y += playerSpeed;
            moving = true;
        }
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if (left && !right) {
      
            g2d.scale(-1, 1);
            g2d.drawImage(animations[playerAction.ordinal()][aniIndex], (int) -x - width, (int) y, width, height, null);
        } else {
        
            g2d.drawImage(animations[playerAction.ordinal()][aniIndex], (int) x, (int) y, width, height, null);
        }

    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
