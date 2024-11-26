package entity.efffect;

import java.awt.Graphics2D;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EffectManager {

    private static EffectManager instance;

    private Map<EffectType, List<Effect>> effectPool;
    private List<Effect> effects;

    private EffectManager() {
        effectPool = new HashMap<>();
        effects = new ArrayList<>();
        initEffectPool();
    }

    public static EffectManager getInstance() {
        if (instance == null) {
            instance = new EffectManager();
        }
        return instance;
    }

    private void initEffectPool() {

        for (EffectType effectType : EffectType.values()) {
            effectPool.put(effectType, new ArrayList<>());
        }

    }

    public void playEffect(EffectType effectType, float x, float y) {
        Effect effect = getEffectFromPool(effectType);
        effect.setActive(true);
        effect.play(x, y);
        effects.add(effect);

    }

    public void playEffect(EffectType effectType, float x, float y, boolean isLooping) {
        Effect effect = getEffectFromPool(effectType);
        effect.setActive(true);
        effect.play(x, y, isLooping);
        effects.add(effect);
    }

    public void playEffect(EffectType effectType, float x, float y,int width, int height, boolean isLooping) {
        Effect effect = getEffectFromPool(effectType);
        effect.setActive(true);
        effect.play(x, y, width, height, isLooping);
        effects.add(effect);
    }

    public void update() {
        if (!effects.isEmpty()) {
            for (Effect effect : effects) {
                if (effect.getIsActive()) {
                    effect.update();
                }
            }
        }
    }

    public void render(Graphics2D g2d) {
        if (!effects.isEmpty()) {
            for (Effect effect : effects) {
                if (effect.getIsActive()) {
                    effect.render(g2d);
                }
            }
        }
    }

    public void resetActiveEffectPool() {
        for (Effect effect : effects) {
            effect.setActive(false);
        }
    }

    private Effect getEffectFromPool(EffectType effectType) {
        List<Effect> pool = effectPool.get(effectType);

        for (Effect effect : pool) {
            if (!effect.getIsActive() && !effect.isFinished())  {
                return effect;
            }
        }

        Effect newEffect = new Effect(0, 0, 100, 100, effectType);
        pool.add(newEffect);
        return newEffect;
    }

    public Effect getEffect(EffectType effectType) {
        List<Effect> pool = effectPool.get(effectType);
        if (pool.isEmpty()) {
            Effect newEffect = new Effect(0, 0, 100, 100, effectType);
            pool.add(newEffect);
            return newEffect;
        }
        return pool.get(0);
    }
}
