package concurso;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AssetManager {
    private Image taxiSprite;
    private Image microbusSprite;
    private Image bacheSprite;
    private Image hamburguesaSprite; // 1. Nueva variable

    public AssetManager() {
        loadImages();
    }

    private void loadImages() {
        try {
            this.taxiSprite = ImageIO.read(getClass().getResource("/taxi.png"));
            this.microbusSprite = ImageIO.read(getClass().getResource("/microbus.png"));
            this.bacheSprite = ImageIO.read(getClass().getResourceAsStream("/bache.png"));
            // 2. Cargamos la imagen de la hamburguesa
            this.hamburguesaSprite = ImageIO.read(getClass().getResource("/hamburguesa.png"));
        } catch (Exception e) {
            System.out.println("Error cargando imágenes: " + e.getMessage());
        }
    }

    public void playSound(String soundName) {
        new Thread(() -> {
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/" + soundName));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception e) {
                System.out.println("Error reproduciendo sonido: " + e.getMessage());
            }
        }).start();
    }

    public Image getTaxiSprite() {
        return taxiSprite;
    }

    public Image getMicrobusSprite() {
        return microbusSprite;
    }

    public Image getBacheSprite() {
        return bacheSprite;
    }

    public Image getHamburguesaSprite() {
        return hamburguesaSprite;
    } // 3. Getter
}
