package concurso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GamePanel extends JPanel implements KeyListener {
    private final Taxi taxi;
    private final CopyOnWriteArrayList<Obstacle> obstacles;
    private final AssetManager assets;
    private final GameEngine engine;

    private volatile double stress = 0.0;
    private volatile int score = 0;
    private volatile boolean gameOver = false;

    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(50, 50, 50));
        setFocusable(true);
        addKeyListener(this);

        // Posición inicial del taxi centrada para 256 de ancho
        this.taxi = new Taxi(width / 2 - 8, height - 50, width);
        this.obstacles = new CopyOnWriteArrayList<>();
        this.assets = new AssetManager();
        this.engine = new GameEngine(this, width, height);

        engine.start();
    }

    public void refresh() {
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar líneas viales
        g.setColor(Color.WHITE);
        for (int i = 0; i < getHeight(); i += 60) {
            g.fillRect(getWidth() / 2 - 2, i + (score % 60), 4, 20);
        }

        for (Obstacle obs : obstacles)
            obs.draw(g, assets);
        taxi.draw(g, assets);
        drawUI(g);
    }

    private void drawUI(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(5, 5, 100, 15);
        g.setColor(stress < 50 ? Color.GREEN : (stress < 80 ? Color.YELLOW : Color.RED));
        g.fillRect(5, 5, (int) stress, 15);
        g.setColor(Color.WHITE);
        g.drawRect(5, 5, 100, 15);
        g.setFont(new Font("Monospaced", Font.BOLD, 10));
        g.drawString("ESTRÉS", 10, 16);

        g.drawString("PUNTOS: " + score, getWidth() - 90, 15);

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Monospaced", Font.BOLD, 18));
            String msg = stress >= 100 ? "PATATÚS" : "CHOQUE";
            g.drawString(msg, getWidth() / 2 - g.getFontMetrics().stringWidth(msg) / 2, getHeight() / 2 - 10);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
            String msg2 = "Presiona ENTER";
            g.drawString(msg2, getWidth() / 2 - g.getFontMetrics().stringWidth(msg2) / 2, getHeight() / 2 + 15);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            // SI ESTÁS MUERTO Y PRESIONAS ENTER, REINICIA EL JUEGO
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                stress = 0.0;
                score = 0;
                obstacles.clear();
                taxi.setX(getWidth() / 2 - 8); // Regresa el taxi al centro
                gameOver = false;
            }
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            taxi.moveLeft();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            taxi.moveRight();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public CopyOnWriteArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public AssetManager getAssets() {
        return assets;
    }

    public double getStress() {
        return stress;
    }

    public void setStress(double stress) {
        this.stress = stress;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public static void main(String[] args) {
        GamePanel juego = new GamePanel(256, 240);
        javax.swing.JFrame ventana = new javax.swing.JFrame("Taxi CDMX");
        ventana.add(juego);
        ventana.pack();
        ventana.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
}
