package JavaTag01;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    private static final Color SUCCESS = new Color(120, 190, 130);
    private static final Color DANGER = new Color(210, 105, 95);

    private final JFrame frame;
    private final JTextArea storyArea;
    private final JTextArea inventoryArea;
    private final JLabel statusLabel;
    private final JTextField codeField;
    private final JButton codeButton;
    private final GameState gameState;

    public GameWindow() {
        frame = new JFrame("The Mystery Hotel - Room 217");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1040, 740);
        frame.setMinimumSize(new Dimension(900, 650));
        frame.setLocationRelativeTo(null);

        gameState = new GameState();
        storyArea = new JTextArea();
        inventoryArea = new JTextArea();
        statusLabel = new JLabel("STATUS: ERMITTLE DIE WAHRHEIT");
        codeField = new JTextField(8);
        codeButton = new JButton("CODE PRÜFEN");

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

        codeButton.addActionListener(event -> checkCode());
        codeField.addActionListener(event -> checkCode());
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
            "\"Vier Hinweise. Eine Tür. Keine zweite Chance.\"\n\n" +
            "Untersuche die vier Gegenstände. Jeder Hinweis wird später gebraucht."
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

        JLabel inventoryTitle = new JLabel("ERMITTLUNG");
        inventoryTitle.setForeground(ACCENT);
        inventoryTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

        inventoryArea.setEditable(false);
        inventoryArea.setForeground(MUTED);
        inventoryArea.setBackground(PANEL);
        inventoryArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inventoryArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        updateInformationPanel();

        panel.add(inventoryTitle, BorderLayout.NORTH);
        panel.add(inventoryArea, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel outer = new JPanel(new BorderLayout(8, 8));
        outer.setBackground(BACKGROUND);
        outer.setBorder(BorderFactory.createEmptyBorder(0, 14, 14, 14));

        JPanel objectPanel = new JPanel(new GridLayout(1, 4, 8, 8));
        objectPanel.setOpaque(false);

        objectPanel.add(createObjectButton("TÜR", "door"));
        objectPanel.add(createObjectButton("UHR", "clock"));
        objectPanel.add(createObjectButton("BILD", "painting"));
        objectPanel.add(createObjectButton("SCHRANK", "cabinet"));

        outer.add(objectPanel, BorderLayout.NORTH);
        outer.add(createCodePanel(), BorderLayout.CENTER);
        outer.add(createStatusPanel(), BorderLayout.SOUTH);

        return outer;
    }

    private JPanel createCodePanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 75, 84)),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        JLabel label = new JLabel("SCHRANK-CODE");
        label.setForeground(ACCENT);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));

        codeField.setHorizontalAlignment(JTextField.CENTER);
        codeField.setFont(new Font("Monospaced", Font.BOLD, 18));
        codeField.setBackground(new Color(25, 28, 34));
        codeField.setForeground(TEXT);
        codeField.setCaretColor(TEXT);
        codeField.setBorder(BorderFactory.createLineBorder(new Color(85, 90, 100)));

        codeButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        codeButton.setForeground(TEXT);
        codeButton.setBackground(new Color(48, 53, 62));
        codeButton.setFocusPainted(false);
        codeButton.setBorder(BorderFactory.createLineBorder(new Color(85, 90, 100)));

        panel.add(label, BorderLayout.WEST);
        panel.add(codeField, BorderLayout.CENTER);
        panel.add(codeButton, BorderLayout.EAST);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        statusLabel.setForeground(MUTED);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(statusLabel);

        return panel;
    }

    private JButton createObjectButton(String label, String object) {
        JButton button = new JButton(label);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(TEXT);
        button.setBackground(new Color(48, 53, 62));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(85, 90, 100)));
        button.addActionListener(event -> inspectObject(object, label));

        return button;
    }

    private void inspectObject(String object, String label) {
        switch (object) {
            case "clock" -> {
                gameState.inspectClock();
                storyArea.setText(
                    "Die Uhr steht bei 10:15. Der Sekundenzeiger bewegt sich nicht.\n\n" +
                    "Auf der Rückseite findest du eine eingeritzte Nachricht:\n\n" +
                    "\"Die Stunde ist die erste Zahl. Ignoriere die Minuten.\"\n\n" +
                    "Du notierst: 10"
                );
            }
            case "painting" -> {
                gameState.inspectPainting();
                storyArea.setText(
                    "Du hebst das schiefe Gemälde vorsichtig an. Dahinter ist ein X in das Holz geritzt.\n\n" +
                    "Darunter steht:\n\n" +
                    "\"Vier Ecken. Diese Zahl gehört an die zweite Stelle.\"\n\n" +
                    "Du notierst: 4"
                );
            }
            case "door" -> {
                gameState.inspectDoor();
                storyArea.setText(
                    "Du untersuchst das Türschloss. Vier kleine Symbole sind in einer Reihe eingraviert.\n\n" +
                    "Eine winzige Gravur darunter lautet:\n\n" +
                    "\"Zähle die Symbole. Diese Zahl gehört an die dritte Stelle.\"\n\n" +
                    "Du notierst: 4"
                );
            }
            case "cabinet" -> {
                gameState.inspectCabinet();
                storyArea.setText(
                    "Der Schrank besitzt ein vierstelliges Zahlenschloss. Auf dem Holz steht:\n\n" +
                    "\"Beginne mit der Uhr. Dann Bild. Dann Tür. " +
                    "Zum Schluss die letzte Ziffer der Zimmernummer.\"\n\n" +
                    "Die Reihenfolge ist eindeutig. Der letzte Hinweis lautet: Zimmer 217 → 7."
                );
            }
            default -> throw new IllegalArgumentException("Unknown object: " + object);
        }

        statusLabel.setForeground(MUTED);
        statusLabel.setText("UNTERSUCHT: " + label);
        updateInformationPanel();
    }

    private void checkCode() {
        if (gameState.isCabinetUnlocked()) {
            return;
        }

        if (!gameState.isPuzzleReady()) {
            storyArea.setText(
                "Das Zahlenschloss reagiert nicht.\n\n" +
                "Du hast noch nicht alle vier Hinweise untersucht. " +
                "Vielleicht solltest du wirklich jedes Objekt ansehen."
            );
            statusLabel.setText("NOCH NICHT BEREIT");
            return;
        }

        String code = codeField.getText().trim();

        if ("1047".equals(code)) {
            gameState.unlockCabinet();
            storyArea.setText(
                "KLICK.\n\n" +
                "Das Schloss springt auf. Im Schrank liegt ein alter Messingschlüssel " +
                "und ein vergilbter Zettel.\n\n" +
                "Auf dem Zettel steht:\n\n" +
                "\"Wenn du diesen Schlüssel gefunden hast, hat das Hotel dich bemerkt.\"\n\n" +
                "NEUER GEGENSTAND: Messingschlüssel"
            );
            statusLabel.setForeground(SUCCESS);
            statusLabel.setText("RÄTSEL GELÖST: SCHRANK GEÖFFNET");
            codeField.setEnabled(false);
            codeButton.setEnabled(false);
        } else {
            gameState.recordFailedAttempt();
            storyArea.setText(
                "Falscher Code. Das Schloss bleibt still.\n\n" +
                "Du hörst irgendwo im Zimmer ein leises Klicken. " +
                "Vielleicht war das keine Warnung, sondern eine Erinnerung."
            );
            statusLabel.setForeground(DANGER);
            statusLabel.setText("FALSCHER CODE | VERSUCHE: " + gameState.getAttempts());
        }

        updateInformationPanel();
    }

    private void updateInformationPanel() {
        StringBuilder text = new StringBuilder();
        text.append("HINWEISE: ")
            .append(gameState.getClueCount())
            .append(" / 4\n\n");

        text.append("Uhr: ")
            .append(gameState.isClockInspected() ? "GEFUNDEN" : "???")
            .append("\n");
        text.append("Bild: ")
            .append(gameState.isPaintingInspected() ? "GEFUNDEN" : "???")
            .append("\n");
        text.append("Tür: ")
            .append(gameState.isDoorInspected() ? "GEFUNDEN" : "???")
            .append("\n");
        text.append("Schrank: ")
            .append(gameState.isCabinetInspected() ? "UNTERSUCHT" : "???")
            .append("\n\n");

        text.append("RÄTSEL: ")
            .append(gameState.isCabinetUnlocked() ? "1 / 1 GELÖST" : "0 / 1")
            .append("\n");
        text.append("VERSUCHE: ")
            .append(gameState.getAttempts());

        inventoryArea.setText(text.toString());
    }
}
