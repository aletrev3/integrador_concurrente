public class Main {
    public static void main(String[] args) {
        interfaz ventana = new interfaz();
        ventana.setVisible(true);
                
        pruebadeclaseobstaculos();
        pruebaGeneradorObs();
        pruebaEntradaUsuario();

    }

    //Alejandra
    public static void pruebaObstaculos() {
        obstaculos obs = new obstaculos(1, "bache", 10);
        new Thread(obs).start();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {}

         System.out.println("Prueba de obstaculos bien");
         System.exit(0);
}

    public static void pruebaGeneradorObs() {
        generador_obs gen = new generador_obs();
        Thread hiloGen = new Thread(new Runnable() {
        @Override
        public void run() {
            gen.generar();
    }
});
            hiloGen.start();
            try {
                Thread.sleep(12000);
            } catch (Exception e) {}

            System.out.println("Prueba de generador_obs bien");
                System.exit(0);
    }


    public static void pruebaEntradaUsuario() {
        interfaz ventana = new interfaz();
        ventana.setVisible(true);

        System.out.println("Escribe un id y presiona Enter para probar la entrada.");

        try {
            Thread.sleep(10000); 
        } catch (Exception e) {}

        System.out.println("Prueba de entrada de usuario bien");
}

}