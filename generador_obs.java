import java.util.*;

public class generador_obs {
    private interfaz ventana;
    private Map<Integer, obstaculos> mapa = new HashMap<>();

    public generador_obs(interfaz ventana) {
        this.ventana = ventana;
    }

    public void generar() {
        String[] tipos = { "choque", "bache", "manifestacion", "construccion" };
        Random random = new Random();
        int contadorId = 1;

        while (true) {
            String tipo = tipos[random.nextInt(tipos.length)];
            int consumo = random.nextInt(20) + 5;

            obstaculos obs = new obstaculos(contadorId, tipo, consumo);
            mapa.put(contadorId, obs);

            Thread hilo = new Thread(obs);
            hilo.start();

            ventana.agregarBoton(contadorId, tipo);

            contadorId++;

            try {
                Thread.sleep(5000);
            } catch (Exception e) {}
        }
    }

    public void matarPorId(int id) {
        obstaculos obs = mapa.get(id);

        if (obs == null) {
            ventana.mostrar("No existe ese ID");
            return;
        }

        if (!obs.estaActivo()) {
            ventana.mostrar("Ya estaba muerto");
            return;
        }

        obs.detener();
        ventana.marcarMuerto(id);
    }
}
