package concurso;

import java.awt.Graphics;

public class Obstacle extends GameObject {
    // 1. Agregamos HAMBURGUESA a las opciones
    public enum Type {
        MICROBUS, BACHE, HAMBURGUESA
    }

    private final Type type;

    public Obstacle(int x, int y, Type type) {
        // La hamburguesa y el bache medirán 16x16. El microbús 24x48.
        super(x, y, (type == Type.MICROBUS) ? 24 : 16, (type == Type.MICROBUS) ? 48 : 16, 3);
        this.type = type;
    }

    @Override
    public void update() {
        this.y += speed;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void draw(Graphics g, AssetManager assets) {
        if (type == Type.MICROBUS && assets.getMicrobusSprite() != null) {
            g.drawImage(assets.getMicrobusSprite(), x, y, width, height, null);
        } else if (type == Type.BACHE && assets.getBacheSprite() != null) {
            g.drawImage(assets.getBacheSprite(), x, y, width, height, null);
        } else if (type == Type.HAMBURGUESA && assets.getHamburguesaSprite() != null) {
            // 2. Le decimos que dibuje la hamburguesa
            g.drawImage(assets.getHamburguesaSprite(), x, y, width, height, null);
        }
    }
}