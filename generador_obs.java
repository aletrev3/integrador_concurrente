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

        // PARADA DE EMERGENCIA: Verificar si el juego terminó
        while (!sistema.isJuegoTerminado()) { 
            String tipo = tipos[random.nextInt(tipos.length)];
            
            
            int consumo = random.nextInt(15) + 5; 
            if (tipo.equals("manifestacion")) consumo = 30; 

            obstaculos obs = new obstaculos(contadorId, tipo, consumo);
            mapa.put(contadorId, obs);

            Thread hilo = new Thread(obs);
            hilo.start();

            ventana.agregarBoton(contadorId, tipo);

            contadorId++;

            try {
                // Generar obstáculo cada 4 segundos (un poco más rápido)
                Thread.sleep(4000); 
            } catch (Exception e) {}
        }
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
