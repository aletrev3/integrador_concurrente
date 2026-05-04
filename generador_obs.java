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

        System.out.println("Generador de obstáculos detenido.");
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
