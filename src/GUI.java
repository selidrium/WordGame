import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class GUI extends JFrame implements ActionListener {
    private JLabel player1Label, player2Label, player3Label, hostLabel;
    private JTextArea jTextArea;
    private JButton startButton;
    private JMenuItem addPlayerItem, addHostItem, layoutItem;
    private JCheckBoxMenuItem saveMessagesItem;
    private Players[] currentPlayers = new Players[3];
    private Hosts host = new Hosts("Alan", "Garcia");
    private Phrases phrases = new Phrases();
    private Turn turn = new Turn();
    private boolean win = false;
    private int playerIndexCounter = 0;
    private int index = 0;
    private JMenuBar menuBar;
    private JMenu gameMenu, aboutMenu;

    public GUI() {
        setTitle("Game Show");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        setLayout(null);

        // Add the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add the game menu
        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        // Add the add player menu item
        addPlayerItem = new JMenuItem("Add Player");
        addPlayerItem.addActionListener(this);
        gameMenu.add(addPlayerItem);

        // Add the add host menu item
        addHostItem = new JMenuItem("Add Phrase");
        addHostItem.addActionListener(this);
        gameMenu.add(addHostItem);

        // Add the about menu
        aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(aboutMenu);

        // Add the layout menu item
        layoutItem = new JMenuItem("Layout");
        layoutItem.addActionListener(this);
        aboutMenu.add(layoutItem);

        // Add the save messages checkbox menu item
        saveMessagesItem = new JCheckBoxMenuItem("Save Messages");
        saveMessagesItem.addActionListener(this);
        gameMenu.add(saveMessagesItem);

        player1Label = new JLabel("Players 01: ");
        add(player1Label);
        player1Label.setBounds(20, 40, 200, 40);

        player2Label = new JLabel("Players 02: ");
        add(player2Label);
        player2Label.setBounds(20, 80, 200, 40);

        player3Label = new JLabel("Players 03: ");
        add(player3Label);
        player3Label.setBounds(20, 120, 200, 40);

        hostLabel = new JLabel("Host : "+host.getFirstName()+"  "+host.getLastName()+"");
        add(hostLabel);
        hostLabel.setBounds(440, 20, 160, 40);

        jTextArea = new JTextArea("Playing Phrase: ");
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        scrollPane.setBounds(300, 80, 590, 410);
        jTextArea.setEditable(false);

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        add(startButton);
        startButton.setBounds(600, 520, 140, 50);

        DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerItem ) {
            if (playerIndexCounter == 3) {
                JOptionPane.showMessageDialog(null, "Only three players are allowed.");
                return;
            }
            String playerName = JOptionPane.showInputDialog(this, "Enter player name:");
            if (playerName != null && !playerName.isEmpty()) {
                Players newPlayer = new Players(playerName);
                currentPlayers[playerIndexCounter] = newPlayer;
                playerIndexCounter++;
                updatePlayersLabel(playerName);
            }
        } else if (e.getSource() == addHostItem) {
            String phrase = JOptionPane.showInputDialog(this, "The phrase to guess is:");
            if (phrase != null && !phrase.isEmpty()) {
                phrases.setGamePhrase(phrase.toLowerCase());
            }
        } else if (e.getSource() == startButton) {
            if (host == null) {
                JOptionPane.showMessageDialog(this, "Please add a host first.");
                return;
            } else if (playerIndexCounter != 3) {
                JOptionPane.showMessageDialog(this, "Please add 3 players.");
                return;
            }
            while (true) {
                win = turn.takeTurn(currentPlayers[index], host, jTextArea, this);
                index = (index + 1) % currentPlayers.length;
                if (win) {
                    int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                }
            }
        }
    }

    public void updatePlayersLabel(String playerName) {
        if (playerIndexCounter == 1) {
            player1Label.setText("Players 01: " + playerName);
        } else if (playerIndexCounter == 2) {
            player2Label.setText("Players 02: " + playerName);
        } else if (playerIndexCounter == 3) {
            player3Label.setText("Players 03: " + playerName);
        }
    }

    public void resetGame() {
        if(!saveMessagesItem.isSelected())
            jTextArea.setText("Playing Phrase: ");
        playerIndexCounter = 0;
        index = 0;
        win = false;
        host = new Hosts("Alan", "Garcia");
        phrases = new Phrases();

        String phrase = JOptionPane.showInputDialog(this, "The phrase to guess is:");
        if (phrase != null && !phrase.isEmpty()) {
            phrases.setGamePhrase(phrase.toLowerCase());
        }
    }

    public static void main(String[] args) {
        GUI gameShow = new GUI();
    }

}