import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.text.DefaultCaret;

public class GUI extends JFrame implements ActionListener {
    private JLabel player1Label,player2Label,player3Label, hostLabel;
    private JTextArea jTextArea;
    private JButton addPlayerButton, addHostButton, startButton;
    Hosts host = new Hosts("Alan", "Garcia");
    Phrases phrases = new Phrases();
    Turn turn = new Turn();
    boolean win = false;
    Players[] currentPlayers = new Players[3];
    int playerIndexCounter=0;
    private String playingPhrase;
    int index=0;
    public GUI() {
        setTitle("Game Show");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        setLayout(null);


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
        add(jTextArea);
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        jTextArea.setBounds(300, 80, 590, 410);
        scrollPane.setBounds(300, 80, 590, 410);

        jTextArea.setEditable(false);
        addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(this);
        add(addPlayerButton);
        addPlayerButton.setBounds(200, 520,140, 50);

        DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        addHostButton = new JButton("Add Phrase");
        addHostButton.addActionListener(this);
        add(addHostButton);

        addHostButton.setBounds(400, 520, 140, 50);
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        add(startButton);


        startButton.setBounds(600,520,140,50);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerButton) {

            if(playerIndexCounter==3)
            {
                JOptionPane.showMessageDialog(null, "Only Three Players Are allowed");
                return;
            }
            String playerName = JOptionPane.showInputDialog(this, "Enter player name:");

            if (playerName != null && !playerName.isEmpty() ) {
                Players newPlayer = new Players(playerName);
                currentPlayers[playerIndexCounter]=newPlayer;
                playerIndexCounter++;
                updatePlayersLabel(playerName);

            }
        }


        else if (e.getSource() == addHostButton) {
            String phrase = JOptionPane.showInputDialog(this, "The phrase to guess is");
            phrases.setGamePhrase(phrase.toLowerCase());



        } else if (e.getSource() == startButton) {
            if (host == null) {
                JOptionPane.showMessageDialog(this, "Please add a host first.");
                return;
            }
            else if (playerIndexCounter != 3 ) {
                JOptionPane.showMessageDialog(this, "Please add 3 Players");
                return;
            }

            while (true) {

                win = turn.takeTurn(currentPlayers[index], host,jTextArea,this);
                index = (index + 1) % currentPlayers.length;

                if (win) {
                    int choice = JOptionPane.showConfirmDialog(
                            null, // parent component
                            "Do you want to play again?", // message to display
                            "Confirmation", // title of dialog box
                            JOptionPane.YES_NO_OPTION, // type of option
                            JOptionPane.QUESTION_MESSAGE // type of message
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        String phrase = JOptionPane.showInputDialog(this, "The phrase to guess is");
                        phrases.setGamePhrase(phrase.toLowerCase());
                        win = false;
                        jTextArea.setText("");

                    } else if (choice == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null,"Thanks for playing ");
                        System.exit(0);

                    } else {


                        JOptionPane.showMessageDialog(null,"Thanks for playing ");
                        System.exit(0);

                    }


                }
            }


        }
    }

    private void updatePlayersLabel(String playerName) {


        if(playerIndexCounter==1)
        {

            player1Label.setText("Player 01 Name : "+playerName);

        }
        else if (playerIndexCounter==2)
        {
            player2Label.setText("Player 02 Name : "+playerName);

        }

        else if(playerIndexCounter==3)
        {
            player3Label.setText("Player 03 Name : "+playerName);

        }

    }
}