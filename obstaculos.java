public class obstaculos implements Runnable {
    private int obs_id;
    private String obs_tipo;
    private int obs_consumo;

    public obstaculos(int obs_id, String obs_tipo, int obs_consumo) {
        this.obs_id = obs_id;
        this.obs_tipo = obs_tipo;
        this.obs_consumo = obs_consumo;
    }

private boolean activo = true;

public void run() {
    while (activo) {
        System.out.println("Obstaculo " + obs_id + obs_tipo + " activo");

<<<<<<< HEAD
    public long getTiempoCreacion() {
        return tiempoCreacion;
    }

    public boolean estaActivo() {
        return activo;
    }

    public long tiempoActivo() {
        return System.currentTimeMillis() - tiempoCreacion;
    }

    public void detener() {
        activo = false;
        System.out.println("Obstáculo " + obs_id + " estuvo activo durante " + tiempoActivo() + "ms");
        sistema.liberarMemoria(obs_consumo); // Se recupera la memoria al morir
    }

    public void run() {
        // Consumimos memoria
        System.out.println("Obstaculo " + obs_id + " (" + obs_tipo + ") bloqueando el paso.");
        sistema.usarMemoria(obs_consumo);

        while (activo && !sistema.isJuegoTerminado()) {
            System.out.println("Obstaculo " + obs_id + " " + obs_tipo + " activo");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Obstaculo " + obs_id + " terminado");
=======
>>>>>>> 548e64b3d29ef02731b9deb520fb199264864afe
    }
}

}