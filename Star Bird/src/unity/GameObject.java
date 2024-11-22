package unity;

public abstract class GameObject {

    protected Vector2D position;
    protected int width, height;
    protected Collider2D collider2D;
    protected boolean isActive = true;

    protected GameObject(float x, float y, int width, int height) {
        this.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        collider2D = new Collider2D(x, y, width, height);
    }   
    
    protected void update() {

    }

    protected void render() {

    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Collider2D getCollider2D() {
        return collider2D;
    }

    public void setCollider2D(Collider2D collider2d) {
        collider2D = collider2d;
    }

    public void setCollider2D(Vector2D position) {
        collider2D = new Collider2D(position.x, position.y, width, height);
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


}
