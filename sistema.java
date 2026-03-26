public class sistema {
    public static int memoria = 100;

    public synchronized static void usarMemoria(int cantidad) {

        memoria -= cantidad;

        System.out.println("Memoria restante: " + memoria);

        // SIMULACIÓN DE DEADLOCK
        // Si la memoria se agota, el sistema colapsa
        if (memoria <= 0) {
            System.out.println("DEADLOCK: sistema colapsado");
            System.exit(0);
        }
    }
}
