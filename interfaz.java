// Archivo: interfaz.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

// Implementamos GameListener para saber cuando termina el juego
public class interfaz extends JFrame implements GameListener {

    private JPanel panelObstaculos; // Panel para los botones
    private JLayeredPane areaJuego;  // Panel superpuesto para taxi y Game Over
    private JTextField campo;
    private JButton matar;
    private JLabel labelMemoria; // Nueva label para mostrar el estrés

    private generador_obs generador;
    private Taxista taxista;
    private Map<Integer, JButton> botones = new HashMap<>();

    public interfaz() {
        setTitle("Simulador Taxista CDMX - Gestión de Estrés");
        setSize(800, 600); // Ventana más grande
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Registrar esta ventana como oyente del sistema
        sistema.setGameListener(this);

        // --- Estructura Principal ---
        setLayout(new BorderLayout());

        // 1. Panel Superior (Controles)
        JPanel arriba = new JPanel();
        arriba.setBackground(Color.LIGHT_GRAY);
        campo = new JTextField(5);
        matar = new JButton("Solucionar Problema (Matar ID)");
        labelMemoria = new JLabel("Estrés Actual: 100 / 100");
        labelMemoria.setFont(new Font("Arial", Font.BOLD, 14));
        labelMemoria.setForeground(Color.BLUE);

        arriba.add(labelMemoria);
        arriba.add(new JLabel(" | Solucionar ID:"));
        arriba.add(campo);
        arriba.add(matar);
        add(arriba, BorderLayout.NORTH);

        // 2. Panel Lateral Derecha (Lista de Obstáculos)
        panelObstaculos = new JPanel();
        panelObstaculos.setLayout(new BoxLayout(panelObstaculos, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panelObstaculos);
        scroll.setPreferredSize(new Dimension(200, 0));
        scroll.setBorder(BorderFactory.createTitledBorder("Eventos en CDMX"));
        add(scroll, BorderLayout.EAST);

        // 3. Área Central de Juego (Donde corre el taxi)
        areaJuego = new JLayeredPane();
        areaJuego.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        areaJuego.setBackground(new Color(100, 100, 100)); // Color asfalto
        areaJuego.setOpaque(true);
        add(areaJuego, BorderLayout.CENTER);

        // --- Inicializar Componentes de Juego ---
        
        // Crear y añadir al Taxista (el cuadrado amarillo)
        taxista = new Taxista();
        taxista.setBounds(300, 200, 30, 30); // Posición y tamaño inicial
        // Añadir a la capa DEFAULT del LayeredPane
        areaJuego.add(taxista, JLayeredPane.DEFAULT_LAYER);

        generador = new generador_obs(this);

        // Acciones
        matar.addActionListener(e -> accionarMatar());
        campo.addActionListener(e -> accionarMatar()); // Enter en el campo también funciona

        // --- Iniciar Hilos ---
        // Hilo del generador de obstáculos
        new Thread(() -> generador.generar()).start();
        // Hilo de animación del taxista
        new Thread(taxista).start();
        // Hilo para actualizar la label de memoria constantemente
        iniciarActualizadorMemoria();
    }
    
    private void accionarMatar() {
        try {
            int id = Integer.parseInt(campo.getText());
            generador.matarPorId(id);
            campo.setText(""); // Limpiar campo
            campo.requestFocus();
        } catch (Exception ex) {
            mostrar("ID inválido");
        }
    }

    private void iniciarActualizadorMemoria() {
        new Thread(() -> {
            while (!sistema.isJuegoTerminado()) {
                SwingUtilities.invokeLater(() -> {
                    labelMemoria.setText("Estrés Actual: " + sistema.memoria + " / 100");
                    // Cambiar color según estrés
                    if (sistema.memoria < 30) labelMemoria.setForeground(Color.RED);
                    else if (sistema.memoria < 60) labelMemoria.setForeground(new Color(255, 140, 0)); // Naranja
                });
                try { Thread.sleep(500); } catch (Exception e) {}
            }
        }).start();
    }

    // Métodos existentes actualizados
    public void agregarBoton(int id, String tipo) {
        SwingUtilities.invokeLater(() -> {
            JButton b = new JButton("ID " + id + " - " + tipo);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(180, 30));
            // Color según gravedad (opcional)
            if(tipo.equals("manifestacion")) b.setBackground(new Color(255, 200, 200));
            
            botones.put(id, b);
            panelObstaculos.add(b);
            panelObstaculos.revalidate();
            panelObstaculos.repaint();
        });
    }

    public void marcarMuerto(int id) {
        SwingUtilities.invokeLater(() -> {
            JButton b = botones.get(id);
            if (b != null) {
                b.setText("ID " + id + " SOLUCIONADO");
                b.setEnabled(false);
                b.setBackground(Color.GRAY);
            }
        });
    }

    public void mostrar(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    // --- Implementación de GameListener ---
    @Override
    public void onGameOver(String mensajeReason) {
        // Detener animación del taxista
        taxista.detener();
        
        // Deshabilitar controles
        matar.setEnabled(false);
        campo.setEnabled(false);

        // Crear el panel de GAME OVER
        JPanel panelGraveOver = new JPanel();
        panelGraveOver.setLayout(new BoxLayout(panelGraveOver, BoxLayout.Y_AXIS));
        panelGraveOver.setBackground(new Color(0, 0, 0, 200)); // Negro semitransparente
        panelGraveOver.setBounds(0, 0, areaJuego.getWidth(), areaJuego.getHeight());

        JLabel labelTitulo = new JLabel("GAME OVER");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 60));
        labelTitulo.setForeground(Color.RED);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelMensaje = new JLabel(mensajeReason);
        labelMensaje.setFont(new Font("Arial", Font.PLAIN, 20));
        labelMensaje.setForeground(Color.WHITE);
        labelMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelGraveOver.add(Box.createVerticalGlue()); // Espaciador superior
        panelGraveOver.add(labelTitulo);
        panelGraveOver.add(Box.createRigidArea(new Dimension(0, 20)));
        panelGraveOver.add(labelMensaje);
        panelGraveOver.add(Box.createVerticalGlue()); // Espaciador inferior

        // Añadir al areaJuego en la capa DRAG (encima de todo)
        areaJuego.add(panelGraveOver, JLayeredPane.DRAG_LAYER);
        areaJuego.revalidate();
        areaJuego.repaint();
    }
}