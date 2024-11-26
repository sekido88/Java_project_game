package entity.item;

public enum ItemState {
    SHOOTING_STAR("shooting_star",false),
    SHIELD("shield",false);

    private String name;
    private boolean isEffect;
    
    ItemState(String name,boolean isEffect) {
        this.name = name;
        this.isEffect = isEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEffect() {
        return isEffect;
    }

    public void setEffect(boolean isEffect) {
        this.isEffect = isEffect;
    }

}
