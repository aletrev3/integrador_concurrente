
import java.util.Random;

public class generador_obs {
<<<<<<< HEAD
    private interfaz ventana;
    private Map<Integer, obstaculos> mapa = new HashMap<>();

    public generador_obs(interfaz ventana) {
        this.ventana = ventana;
    }

    // Nayeli
=======
>>>>>>> 548e64b3d29ef02731b9deb520fb199264864afe
    public void generar() {
        String[] tipos = {"choque", "bache", "manifestacion", "construccion"};
        Random random = new Random();
        int contadorId = 1;

<<<<<<< HEAD
        while (!sistema.isJuegoTerminado()) {

=======
        while (true) {
>>>>>>> 548e64b3d29ef02731b9deb520fb199264864afe
            String tipo = tipos[random.nextInt(tipos.length)];
            int obs_id = contadorId;
            String obs_tipo = tipo;
            int obs_consumo = random.nextInt(100) + 1;

<<<<<<< HEAD
            int consumo = random.nextInt(15) + 5;

            if (tipo.equals("manifestacion")) {
                consumo = 30;
            }
            obstaculos obs = new obstaculos(
                    contadorId,
                    tipo,
                    consumo);

            mapa.put(contadorId, obs);

            Thread hilo = new Thread(obs);
            hilo.start();

            ventana.agregarBoton(
                    contadorId,
                    tipo);

            contadorId++;

            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Generador de obstáculos detenido.");
=======
            obstaculos obs_1 = new obstaculos(obs_id, obs_tipo, obs_consumo);

            Thread hilo = new Thread(obs_1);
            hilo.start();

            contadorId++;
                    try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
>>>>>>> 548e64b3d29ef02731b9deb520fb199264864afe
    }
}