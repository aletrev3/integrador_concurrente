package concurso;

import java.awt.Graphics;

public class Taxi extends GameObject {
    private final int maxWidth;

    public Taxi(int x, int y, int maxWidth) {
        super(x, y, 16, 24, 10); // Tamaño 16x24, velocidad 10
        this.maxWidth = maxWidth;
    }

    public void setX(int x) {
        this.x = x;
    } // Necesario para reiniciar el juego

    @Override
    public void update() {
    }

    public void moveLeft() {
        if (x > 0)
            x -= speed;
    }

    public void moveRight() {
        if (x < maxWidth - width)
            x += speed;
    }

    @Override
    public void draw(Graphics g, AssetManager assets) {
        if (assets.getTaxiSprite() != null) {
            g.drawImage(assets.getTaxiSprite(), x, y, width, height, null);
        }
    }
}