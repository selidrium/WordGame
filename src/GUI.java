import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
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
    private BufferedImage prizeImage;
    private JLabel prizeImageLabel;
    private JMenuItem imageAttributionItem, soundAttributionItem;
    private Clip correctAnswerClip, incorrectAnswerClip;
    private String imageAttributionText = "Image attribution text";


    public GUI() {

        // (Load image)
        try {
            prizeImage = ImageIO.read(new File("media/images/prize.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // (Load sounds)
        correctAnswerClip = loadSound("media/sounds/sound2.wav");
        incorrectAnswerClip = loadSound("media/sounds/sound2.wav");

        // Add the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add the game menu
        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        // Add the about menu
        aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(aboutMenu);

        // (Add image and sound attribution menu items)
        imageAttributionItem = new JMenuItem("Image Attribution");
        imageAttributionItem.addActionListener(this);
        aboutMenu.add(imageAttributionItem);

        soundAttributionItem = new JMenuItem("Sound Attribution");
        soundAttributionItem.addActionListener(this);
        aboutMenu.add(soundAttributionItem);

        // (Add prize image label)
        prizeImageLabel = new JLabel(new ImageIcon(prizeImage));
        prizeImageLabel.setBounds(500, 100, prizeImage.getWidth(), prizeImage.getHeight());
        add(prizeImageLabel);
        prizeImageLabel.setVisible(false);

        // (Add animation)
        Thread animationThread = new Thread(() -> {
            while (true) {
                animateImage();
            }
        });
        animationThread.start();

        setTitle("Game Show");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        setLayout(null);


        // Add the add player menu item
        addPlayerItem = new JMenuItem("Add Player");
        addPlayerItem.addActionListener(this);
        gameMenu.add(addPlayerItem);

        // Add the add host menu item
        addHostItem = new JMenuItem("Add Phrase");
        addHostItem.addActionListener(this);
        gameMenu.add(addHostItem);


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

    public void showPrizeImage() {
        prizeImageLabel.setVisible(true);
    }

    // (Load sound method)
    private Clip loadSound(String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(0);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }
    // (Animate image method)
    private void animateImage() {
        try {
            int x = 0;
            while (x < this.getWidth()) {
                prizeImageLabel.setLocation(x, prizeImageLabel.getY());
                x += 2;
                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        // (Handle new menu item actions)
        if (e.getSource() == imageAttributionItem) {
            // Show image attribution dialog
            new AttributionDialog("Image Attribution", imageAttributionText);
        }

        // (Handle new menu item actions)
        if (e.getSource() == imageAttributionItem) {
            // Show image attribution dialog
            new AttributionDialog("Image Attribution", "Image 1");
        } else if (e.getSource() == soundAttributionItem) {
            // Show sound attribution dialog
            new AttributionDialog("Sound Attribution", "Sound 1");
        }

        if (win) {
            prizeImageLabel.setVisible(true);
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                loadSound("media/sounds/sound1.mp3");

                showPrizeImage();
                animateImage();
                ImageDisplay.displayImage("media/images/prize.jpeg");


                resetGame();
            } else {
                System.exit(0);
            }
        }


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

    // (New AttributionDialog class)
    static class AttributionDialog extends JDialog {
        public AttributionDialog(String title, String attributionText) {
            setTitle(title);
            setSize(300, 200);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea(attributionText);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);

            setVisible(true);
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

    public class ImageDisplay {

        public static void displayImage(String imagePath) {
            // Create a new JFrame
            JFrame frame = new JFrame("Image Display");

            // Create a new JLabel to hold the image
            JLabel imageLabel = new JLabel();

            // Load the image from the file
            ImageIcon imageIcon = new ImageIcon(imagePath);

            // Set the image on the JLabel
            imageLabel.setIcon(imageIcon);

            // Add the JLabel to the JFrame
            frame.add(imageLabel);

            // Set the JFrame size and visibility
            frame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            frame.setVisible(true);
        }
    }


}