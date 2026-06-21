package concurso;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    // Marcados como volatile para asegurar visibilidad inmediata entre hilos
    protected volatile int x;
    protected volatile int y;
    protected int width;
    protected int height;
    protected int speed;

    public GameObject(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public abstract void update();

    public abstract void draw(Graphics g, AssetManager assets);

    // Caja de colisión (Hitbox)
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}