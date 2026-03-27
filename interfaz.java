import javax.swing.*;
import java.awt.*;
import java.util.*;

public class interfaz extends JFrame {

    private JPanel panel;
    private JTextField campo;
    private JButton matar;

    private generador_obs generador;
    private Map<Integer, JButton> botones = new HashMap<>();

    public interfaz() {
        setTitle("Obstaculos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        campo = new JTextField(5);
        matar = new JButton("Matar");

        JPanel arriba = new JPanel();
        arriba.add(new JLabel("ID:"));
        arriba.add(campo);
        arriba.add(matar);

        add(arriba, BorderLayout.NORTH);
        add(new JScrollPane(panel), BorderLayout.CENTER);

        generador = new generador_obs(this);

        matar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campo.getText());
                generador.matarPorId(id);
            } catch (Exception ex) {
                mostrar("ID invalido");
            }
        });

        new Thread(() -> generador.generar()).start();
    }

    public void agregarBoton(int id, String tipo) {
        SwingUtilities.invokeLater(() -> {
            JButton b = new JButton("ID " + id + " - " + tipo);
            botones.put(id, b);
            panel.add(b);
            panel.revalidate();
        });
    }

    public void marcarMuerto(int id) {
        SwingUtilities.invokeLater(() -> {
            JButton b = botones.get(id);
            if (b != null) {
                b.setText("ID " + id + " FINALIZADO");
                b.setEnabled(false);
            }
        });
    }

    public void mostrar(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}