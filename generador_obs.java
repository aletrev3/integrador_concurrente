import java.util.Random;

public class generador_obs {
    public void generar() {
        String[] tipos = { "choque", "bache", "manifestacion", "construccion" };
        Random random = new Random();
        int contadorId = 1;

        while (true) {
            String tipo = tipos[random.nextInt(tipos.length)];
            int obs_id = contadorId;
            String obs_tipo = tipo;
            int obs_consumo = random.nextInt(20) + 5;

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
    }
}
