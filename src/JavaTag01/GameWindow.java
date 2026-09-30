package JavaTag01;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Main game window for the first playable prototype.
 *
 * The game text remains German while class, method and variable names stay English.
 */
public class GameWindow {
    private final JFrame frame;
    private final JTextArea storyArea;

    public GameWindow() {
        frame = new JFrame("The Mystery Hotel - Room 217");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 620);
        frame.setMinimumSize(new Dimension(820, 520));
        frame.setLocationRelativeTo(null);

        storyArea = new JTextArea();
        configureUi();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void configureUi() {
        frame.setLayout(new BorderLayout(12, 12));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(14, 18, 4, 18));

        JLabel title = new JLabel("THE MYSTERY HOTEL");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.add(title, BorderLayout.WEST);

        JLabel roomLabel = new JLabel("ZIMMER 217");
        roomLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.add(roomLabel, BorderLayout.EAST);

        frame.add(header, BorderLayout.NORTH);

        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        storyArea.setText(
            "Du wachst in einem fremden Hotelzimmer auf.\n\n" +
            "Die Tür ist verschlossen. Auf dem Tisch tickt eine alte Uhr. " +
            "An der Wand hängt ein Bild, und neben dem Bett steht ein verschlossener Schrank.\n\n" +
            "Eine Nachricht liegt vor dir:\n\n" +
            "\"Vier Hinweise. Eine Tür. Keine zweite Chance.\"\n\n" +
            "Untersuche den Raum und finde heraus, was hier passiert ist."
        );

        frame.add(new JScrollPane(storyArea), BorderLayout.CENTER);

        JPanel objectPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        objectPanel.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));
        objectPanel.add(createObjectButton("TÜR", "Die Tür scheint schwer verriegelt zu sein."));
        objectPanel.add(createObjectButton("UHR", "Die Uhr steht still. Der Minutenzeiger zeigt auf 15."));
        objectPanel.add(createObjectButton("BILD", "Hinter dem Bild ist etwas in das Holz geritzt."));
        objectPanel.add(createObjectButton("SCHRANK", "Der Schrank ist verschlossen. Ein kleines X ist eingraviert."));
        frame.add(objectPanel, BorderLayout.SOUTH);
    }

    private JButton createObjectButton(String label, String message) {
        JButton button = new JButton(label);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.addActionListener(event -> storyArea.setText(message));
        return button;
    }
}
