package JavaGameProject.entites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import JavaGameProject.utilz.LoadSave;
import JavaGameProject.levels.LevelManager;
import JavaGameProject.main.Game;
import JavaGameProject.unity.Vector2D;

import static JavaGameProject.utilz.HelpMothods.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 10;
    private PlayerAction playerAction = PlayerAction.Idle;

    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 10 * Game.SCALE;
    private float yDrawOffset = 5 * Game.SCALE;
    private float widthDrawOffset = 25 * Game.SCALE;
    private float heightDrawOffset = 12 * Game.SCALE;

    private float jumpSpeed = 500f * Game.SCALE;
    private float initialJumpSpeed = 2.25f * Game.SCALE;
    private float airSpeed = 10f;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    private Vector2D velocity;
    private Vector2D acceleration;
    private Vector2D gravity = new Vector2D(0, Game.GRAVITY);
    private float mass = 1.0f;

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
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, width - widthDrawOffset, height - heightDrawOffset);
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
    }

    public void applyForce(Vector2D force) {
        float deltaTime = 1.0f / 120.0f;
        this.velocity = this.velocity.addForce2D(force, mass, deltaTime);
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    private void loadAnimations() {
        animations = LoadSave.GetSpriteAnimations(LoadSave.PLAYER_ANIMATIONS, PlayerAction.values());
    }

    public void setAnimationTick(int anispeed) {
        this.aniSpeed = anispeed;
    }

    public void update() {

        updatePos();
        updateHitBox();
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
        inAir = false;
        updatePosX();
        updatePosY();
        moving = left || right;
    }

    private void updatePosX() {

        if (left) {
            velocity.x = -playerSpeed;
        } else if (right) {
            velocity.x = playerSpeed;
        } else {
            velocity.x = 0;
        }
        if (CanMoveHere(hitBox.x + velocity.x, hitBox.y, hitBox.width, hitBox.height, levelData)) {
            this.x += velocity.x;
        }
    }

    private void updatePosY() {

        if (!IsEntityOnFloor(hitBox, levelData) && !inAir) {
            applyForce(gravity);
            inAir = true;
        } else {
            velocity.y = 0;
            if (jump) {
                inAir = true;
                Vector2D forceJump = new Vector2D(0, -jumpSpeed);
                applyForce(forceJump);
            }
        }
        if (CanMoveHere(hitBox.x, hitBox.y + velocity.y, hitBox.width, hitBox.height, levelData)) {
            this.y += velocity.y;
        }
        
    }

    private void drawPlayer(Graphics2D g2d, int xLvlOffset) {
        g2d.drawImage(animations[playerAction.ordinal()][aniIndex], (int) (hitBox.x - xDrawOffset) - xLvlOffset,
                (int) (hitBox.y - yDrawOffset), width, height,
                null);
    }

    public void render(Graphics2D g2d, int xLvlOffset) {
        drawPlayer(g2d, xLvlOffset);
        drawHitBox(g2d);
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        jump = false;
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
}
