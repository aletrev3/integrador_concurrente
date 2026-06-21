package concurso;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*import GamePanel;
import Obstacle;
import Obstacle.Type;*/

public class GameEngine {
    private final GamePanel panel;
    private ScheduledExecutorService executorService;
    private final int width;
    private final int height;

    public GameEngine(GamePanel panel, int width, int height) {
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    public void start() {
        executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(this::updateGame, 0, 16, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(this::spawnTraffic, 1, 1200, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(this::increaseStress, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void updateGame() {
        if (panel.isGameOver()) {
            panel.refresh();
            return;
        }

        panel.setScore(panel.getScore() + 1);

        for (Obstacle obs : panel.getObstacles()) {
            obs.update();

            if (obs.y > height) {
                panel.getObstacles().remove(obs);
                panel.setStress(Math.max(0, panel.getStress() - 1.0));
            }

            if (panel.getTaxi().getBounds().intersects(obs.getBounds())) {
                if (obs.getType() == Obstacle.Type.BACHE) {
                    panel.setStress(panel.getStress() + 15);
                    panel.getAssets().playSound("claxon.wav");
                    panel.getObstacles().remove(obs);
                } else if (obs.getType() == Obstacle.Type.MICROBUS) {
                    panel.getAssets().playSound("choque.wav");
                    panel.setGameOver(true);
                } else if (obs.getType() == Obstacle.Type.HAMBURGUESA) {
                    // --- AQUÍ ESTÁ LA MAGIA DE LA HAMBURGUESA ---
                    // Baja 20 puntos de estrés. El Math.max asegura que no baje de 0 (no hay estrés
                    // negativo)
                    panel.setStress(Math.max(0, panel.getStress() - 20));
                    // Opcional: puedes agregar un sonido al comer
                    // panel.getAssets().playSound("mordida.wav");
                    panel.getObstacles().remove(obs);
                }
            }
        }

        if (panel.getStress() >= 100)
            panel.setGameOver(true);
        panel.refresh();
    }

    private void spawnTraffic() {
        if (panel.isGameOver())
            return;
        int randomX = (int) (Math.random() * (width - 24));

        // --- PROBABILIDADES DE APARICIÓN ---
        double probabilidad = Math.random();
        Obstacle.Type type;

        if (probabilidad > 0.6) {
            type = Obstacle.Type.MICROBUS; // 40% de que salga Microbús
        } else if (probabilidad > 0.2) {
            type = Obstacle.Type.BACHE; // 40% de que salga Bache
        } else {
            type = Obstacle.Type.HAMBURGUESA; // 20% de que salga Hamburguesa
        }

        panel.getObstacles().add(new Obstacle(randomX, -50, type));
    }

    private void increaseStress() {
        if (!panel.isGameOver())
            panel.setStress(panel.getStress() + 0.6);
    }
}