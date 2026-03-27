public class obstaculos implements Runnable {
    private int obs_id;
    private String obs_tipo;
    private int obs_consumo;

    private boolean activo = true;

    public obstaculos(int obs_id, String obs_tipo, int obs_consumo) {
        this.obs_id = obs_id;
        this.obs_tipo = obs_tipo;
        this.obs_consumo = obs_consumo;
    }

    public int getId() {
        return obs_id;
    }

    public String getTipo() {
        return obs_tipo;
    }

    public void detener() {
        activo = false;
        sistema.liberarMemoria(obs_consumo);
    }

    public boolean estaActivo() {
        return activo;
    }

    public void run() {
        while (activo && !sistema.isJuegoTerminado()) {
            System.out.println("Obstaculo " + obs_id + " " + obs_tipo + " activo");
            sistema.usarMemoria(obs_consumo);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Obstaculo " + obs_id + " terminado");
    }
}