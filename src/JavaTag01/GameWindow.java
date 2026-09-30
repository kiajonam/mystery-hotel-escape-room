package JavaTag01;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Main game window for the first playable room.
 *
 * Game text is German. Source code remains English.
 */
public class GameWindow {
    private static final Color BACKGROUND = new Color(24, 27, 32);
    private static final Color PANEL = new Color(34, 38, 45);
    private static final Color ACCENT = new Color(196, 154, 73);
    private static final Color TEXT = new Color(235, 235, 235);
    private static final Color MUTED = new Color(165, 170, 178);

    private final JFrame frame;
    private final JTextArea storyArea;
    private final JLabel statusLabel;

    public GameWindow() {
        frame = new JFrame("The Mystery Hotel - Room 217");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1040, 680);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocationRelativeTo(null);

        storyArea = new JTextArea();
        statusLabel = new JLabel("STATUS: ERMITTLE DIE WAHRHEIT");

        configureUi();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void configureUi() {
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout(12, 12));

        frame.add(createHeader(), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));

        centerPanel.add(createRoomPanel(), BorderLayout.CENTER);
        centerPanel.add(createInformationPanel(), BorderLayout.EAST);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PANEL);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("THE MYSTERY HOTEL");
        title.setForeground(ACCENT);
        title.setFont(new Font("Serif", Font.BOLD, 28));

        JLabel room = new JLabel("ZIMMER 217");
        room.setForeground(TEXT);
        room.setFont(new Font("SansSerif", Font.BOLD, 16));

        header.add(title, BorderLayout.WEST);
        header.add(room, BorderLayout.EAST);

        return header;
    }

    private JPanel createRoomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 75, 84)),
            BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel roomTitle = new JLabel("DAS ZIMMER");
        roomTitle.setForeground(MUTED);
        roomTitle.setFont(new Font("SansSerif", Font.BOLD, 13));

        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setForeground(TEXT);
        storyArea.setBackground(PANEL);
        storyArea.setCaretColor(TEXT);
        storyArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        storyArea.setBorder(BorderFactory.createEmptyBorder(12, 4, 12, 4));
        storyArea.setText(
            "Du wachst in einem fremden Hotelzimmer auf.\n\n" +
            "Die Tür ist verschlossen. Eine alte Uhr steht still. " +
            "Ein Gemälde hängt schief an der Wand. Neben dem Bett steht ein alter Schrank.\n\n" +
            "Auf dem Tisch liegt eine Nachricht:\n\n" +
            ""Vier Hinweise. Eine Tür. Keine zweite Chance."\n\n" +
            "Untersuche die Gegenstände. Irgendetwas hier ergibt keinen Sinn."
        );

        panel.add(roomTitle, BorderLayout.NORTH);
        panel.add(new JScrollPane(storyArea), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInformationPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(220, 0));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 75, 84)),
            BorderFactory.createEmptyBorder(14, 14, 14, 14)
        ));

        JLabel inventoryTitle = new JLabel("INVENTAR");
        inventoryTitle.setForeground(ACCENT);
        inventoryTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

        JTextArea inventory = new JTextArea(
            "Noch keine Gegenstände.\n\n" +
            "Hinweise gefunden: 0\n" +
            "Rätsel gelöst: 0"
        );
        inventory.setEditable(false);
        inventory.setForeground(MUTED);
        inventory.setBackground(PANEL);
        inventory.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inventory.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(inventoryTitle, BorderLayout.NORTH);
        panel.add(inventory, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel outer = new JPanel(new BorderLayout(8, 8));
        outer.setBackground(BACKGROUND);
        outer.setBorder(BorderFactory.createEmptyBorder(0, 14, 14, 14));

        JPanel objectPanel = new JPanel(new GridLayout(1, 4, 8, 8));
        objectPanel.setOpaque(false);

        objectPanel.add(createObjectButton("TÜR",
            "Die Tür ist massiv. Unter dem Schloss erkennst du vier kleine Symbole."));
        objectPanel.add(createObjectButton("UHR",
            "Die Uhr steht still. Der Minutenzeiger zeigt auf 15. Auf der Rückseite steht: "Zeit ist ein Hinweis.""));
        objectPanel.add(createObjectButton("BILD",
            "Du hebst das Gemälde an. Dahinter wurde ein X in das Holz geritzt."));
        objectPanel.add(createObjectButton("SCHRANK",
            "Der Schrank ist verschlossen. Auf dem Schloss sind vier kleine Vertiefungen."));

        outer.add(objectPanel, BorderLayout.CENTER);
        outer.add(createStatusPanel(), BorderLayout.SOUTH);

        return outer;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        statusLabel.setForeground(MUTED);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(statusLabel);

        return panel;
    }

    private JButton createObjectButton(String label, String message) {
        JButton button = new JButton(label);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(TEXT);
        button.setBackground(new Color(48, 53, 62));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(85, 90, 100)));
        button.addActionListener(event -> {
            storyArea.setText(message);
            statusLabel.setText("UNTERSUCHT: " + label);
        });

        return button;
    }
}
