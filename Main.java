public class Main {
    public static void main(String[] args) {
        interfaz ventana = new interfaz();
        ventana.setVisible(true);

        pruebadeclaseobstaculos();
        pruebaValidacionYMonitor();
    }

    // Alejandra
    public static void pruebadeclaseobstaculos() {
        obstaculos obs = new obstaculos(1, "bache", 10);

        Thread hilo = new Thread(obs);
        hilo.start();
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
        }
        obs.detener();

        System.out.println("Prueba de obstaculos bien");
    }

    public static void pruebaGenerador() {
        interfaz ventana = new interfaz();
        generador_obs gen = new generador_obs(ventana);
        gen.generar();
        gen.matarPorId(1);

        System.out.println("Prueba de generador bien");
    }

    public static void pruebaEntradaUsuario() {
        interfaz ventana = new interfaz();
        ventana.setVisible(true);

        System.out.println("Escribe un id y presiona Enter para probar la entrada.");

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }

        System.out.println("Prueba de entrada de usuario bien");
    }

   // Nayeli
    public static void pruebaValidacionYMonitor() {
        boolean pasoLibre = !sistema.hayBloqueo();
        System.out.println("RF-02 Validación previa: " + pasoLibre);

        sistema.usarMemoria(10);
        pasoLibre = !sistema.hayBloqueo();
        System.out.println("RF-02 Validación bloqueado: " + pasoLibre);
        sistema.liberarMemoria(10);

        boolean colapso = sistema.isJuegoTerminado();
        System.out.println("RF-05 Monitor inicial: " + colapso);

        sistema.usarMemoria(100);
        colapso = sistema.isJuegoTerminado();
        System.out.println("RF-05 Monitor Deadlock activado: " + colapso);
    }
}
