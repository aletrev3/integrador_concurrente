// Archivo: sistema.java
import javax.swing.SwingUtilities;

public class sistema {
    // Bajamos la memoria inicial para que el juego sea más rápido/difícil
    public static int memoria = 100; 
    private static GameListener oyenteGame;
    private static boolean juegoTerminado = false;

    // Método para suscribir la GUI
    public static void setGameListener(GameListener listener) {
        oyenteGame = listener;
    }

    public synchronized static void usarMemoria(int cantidad) {
        if (juegoTerminado) return; // No hacer nada si ya perdimos

        memoria -= cantidad;
        System.out.println("Memoria restante (Estrés): " + memoria);

        // Si la memoria se agota, el taxista colapsa
        if (memoria <= 0) {
            memoria = 0; // No mostrar valores negativos
            juegoTerminado = true;
            System.out.println("DEADLOCK: El taxista ha colapsado por estrés.");
            
            // Notificar a la GUI de forma segura en el hilo de Swing
            if (oyenteGame != null) {
                SwingUtilities.invokeLater(() -> oyenteGame.onGameOver("El taxista ha sufrido un ataque de estrés. CDMX gana."));
            }
        }
    }
    
    public synchronized static boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}