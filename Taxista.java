
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Taxista extends JPanel implements Runnable {
    private int x = 250; 
    private int y = 150;
    private final int TAMANO = 30; 
    private boolean activo = true;
    private boolean enEspera = false; 
    private Random random = new Random();

    public Taxista() {
        setOpaque(false);
        setPreferredSize(new Dimension(TAMANO, TAMANO));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (enEspera) {
            g.setColor(Color.RED); 
        } else {
            g.setColor(Color.YELLOW);
        }
        g.fillRect(0, 0, TAMANO, TAMANO);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, TAMANO, TAMANO);
    }

    public void detener() {
        this.activo = false;
    }

    @Override
    public void run() {
        while (activo && !sistema.isJuegoTerminado()) {
            
            
            if (sistema.hayBloqueo()) {
                enEspera = true;
                SwingUtilities.invokeLater(() -> repaint()); 
            } else {
                enEspera = false;
                
                // Lógica original de movimiento
                x += random.nextInt(11) - 5; 
                y += random.nextInt(11) - 5; 

                x = Math.max(10, Math.min(x, 350));
                y = Math.max(10, Math.min(y, 250));

                SwingUtilities.invokeLater(() -> {
                    setLocation(x, y);
                    repaint(); 
                });
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
