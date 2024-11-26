package entity.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.efffect.Effect;
import entity.star.Star;

import java.awt.Graphics2D;
import main.Game;
import entity.efffect.EffectType;
import entity.player.Player;

public class Shield extends Item {

    private float effectDuration = 60.0f;
    private float effectTimer = 0;

    private Player player;
    private Effect effect;

    public Shield(float x, float y) {
        super(x, y, ItemType.SHIELD);
        effect = Game.getInstance().getEffectManager().getEffect(EffectType.SHIELD);
        player = Game.getInstance().getPlaying().getPlayer();
    }

    protected void onCollect() {
        super.onCollect();
        Game.getInstance().getAudioPlayer().play("happy_momment", false);
        isEffectActive = true;
        ItemState.SHIELD.setEffect(true);
        effect.play(player.getX(), player.getY(), player.getWidth(), player.getHeight(), true);
    }

    public void update() {
        super.update();
        if (ItemState.SHIELD.isEffect()) {
            effectTimer += Game.getInstance().DELTA_TIME;

            effect.setPosition(Game.getInstance().getPlaying().getPlayer().getPosition());
            effect.update();

            if (effectTimer >= effectDuration) {
                ItemState.SHIELD.setEffect(false);
                isEffectActive = false;
                effectTimer = 0;
            }
        }
    }

    public void render(Graphics2D g2d) {
        super.render(g2d);
        if (ItemState.SHIELD.isEffect()) {
            effect.render(g2d);
        }
    }
}
