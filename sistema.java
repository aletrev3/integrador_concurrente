
import javax.swing.SwingUtilities;

public class sistema {
    public static int memoria = 100;
    // NUEVO: Contador para saber si el taxista debe detenerse
    public static int obstaculosActivos = 0;

    private static GameListener oyenteGame;
    private static boolean juegoTerminado = false;

    public static void setGameListener(GameListener listener) {
        oyenteGame = listener;
    }

    public synchronized static void usarMemoria(int cantidad) {
        if (juegoTerminado)
            return;

        memoria -= cantidad;
        obstaculosActivos++; // NUEVO: Registramos que hay un obstáculo bloqueando
        System.out.println("Memoria restante (Estrés): " + memoria);

        if (memoria <= 0) {
            memoria = 0;
            juegoTerminado = true;
            System.out.println("DEADLOCK: El taxista ha colapsado por estrés.");

            if (oyenteGame != null) {
                SwingUtilities.invokeLater(() -> oyenteGame.onGameOver("El taxista colapsó por estrés. CDMX gana."));
            }
        }
    }

    // Nayeli
    public synchronized static void liberarMemoria(int cantidad) {
        if (juegoTerminado)
            return;

        memoria += cantidad;
        if (obstaculosActivos > 0) {
            obstaculosActivos--;
        }

        if (memoria > 100)
            memoria = 100;
        System.out.println("Memoria recuperada: " + memoria);
    }

    public synchronized static boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    // NUEVO: Método para que el taxista pregunte si puede avanzar
    public synchronized static boolean hayBloqueo() {
        return obstaculosActivos > 0;
    }
}
