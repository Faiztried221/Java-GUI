import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIApp implements ActionListener {

    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> hobbyBox;
    private JCheckBox agreeBox;
    private JButton submitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIApp().createAndShow());
    }

    private void createAndShow() {
        // ===== TOP-LEVEL CONTAINER (FRAME) =====
        frame = new JFrame("Tugas GUI - Frame, Panel, Komponen, Layout, Listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10)); // Layout manager untuk frame

        // ===== ATOMIC COMPONENTS =====
        JLabel titleLabel = new JLabel("Contoh Komponen Atomik", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel nameLabel = new JLabel("Nama:");
        nameField = new JTextField();

        JLabel hobbyLabel = new JLabel("Hobi:");
        hobbyBox = new JComboBox<>(
                new String[] { "Membaca", "Olahraga", "Musik", "Coding" });

        agreeBox = new JCheckBox("Saya setuju");

        submitButton = new JButton("Tampilkan");

        // ===== INTERMEDIATE CONTAINER (PANEL) + LAYOUT =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(hobbyLabel);
        formPanel.add(hobbyBox);
        formPanel.add(agreeBox);
        formPanel.add(new JLabel());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);

        // ===== EVENT LISTENER (delegasi ke method actionPerformed) =====
        submitButton.addActionListener(this);

        // ===== SUSUN KE FRAME =====
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(420, 230);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ===== EVENT METHOD OVERRIDE DARI INTERFACE ActionListener =====
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String hobby = (String) hobbyBox.getSelectedItem();
        boolean agreed = agreeBox.isSelected();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Nama belum diisi!", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!agreed) {
            JOptionPane.showMessageDialog(frame,
                    "Silakan centang 'Saya setuju' terlebih dahulu.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String message = "Halo, " + name + "!\nHobi: " + hobby;
        JOptionPane.showMessageDialog(frame, message,
                "Hasil", JOptionPane.INFORMATION_MESSAGE);
    }
}
