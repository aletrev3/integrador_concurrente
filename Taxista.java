
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Taxista extends JPanel implements Runnable {
    private int x = 250; // Posición inicial
    private int y = 150;
    private final int TAMANO = 30; // Tamaño del cuadrado
    private boolean activo = true;
    private Random random = new Random();

    public Taxista() {
        // panel transparente para que solo se vea el cuadrado
        setOpaque(false);
        setPreferredSize(new Dimension( TAMANO, TAMANO));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar el cuadrado amarillo (el taxi)
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, TAMANO, TAMANO);
        
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, TAMANO, TAMANO);
    }

    public void detener() {
        this.activo = false;
    }

    @Override
    public void run() {
        // Hilo de animación simple: mover el taxi aleatoriamente por "CDMX"
        while (activo && !sistema.isJuegoTerminado()) {
            // Movimiento errático simple
            x += random.nextInt(11) - 5; // -5 a +5
            y += random.nextInt(11) - 5; // -5 a +5

            // Limitar dentro de los bordes del área de juego 
            x = Math.max(10, Math.min(x, 350));
            y = Math.max(10, Math.min(y, 250));

            // Actualizar la posición del componente en el contenedor 
            SwingUtilities.invokeLater(() -> {
                setLocation(x, y);
                repaint(); // Forzar redibujado
            });

            try {
                Thread.sleep(100); // Velocidad de la animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
