public class Main {
    public static void main(String[] args) {

        // Iniciar interfaz del juego N
        interfaz ventana = new interfaz();
        ventana.setVisible(true);

        pruebadeclaseobstaculos();

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

        System.out.println("Prueba completada correctamente");
    }

    public static void pruebaGenerador() {
        interfaz ventana = new interfaz();
        generador_obs gen = new generador_obs(ventana);
        gen.generar();
        gen.matarPorId(1);

        System.out.println("Prueba de generador bien");
    }

    // N
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

}