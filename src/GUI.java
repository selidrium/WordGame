import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    private static Players[] currentPlayers = new Players[0];
//    private static final Hosts host = new Hosts();


    public GUI () {
        JFrame frame = new JFrame("Word Game");

        frame.setSize(800, 800); // set size of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure frame closes when X button is clicked
        frame.setVisible(true); // make frame visible

        // Create a JLabel to display the current players
        JLabel playersLabel = new JLabel("Current players: ");
        playersLabel.setBounds(150,150,300,200);
        frame.add(playersLabel);

        // Create a JLabel to display the current host button
        JButton setHostButton = new JButton("Set Host");
        setHostButton.setBounds(100, 400, 250, 100);
        frame.add(setHostButton);


        // Create a JLabel to display the current host
        JLabel hostLabel = new JLabel();
        hostLabel.setBounds(150,180,300,300);
        frame.add(hostLabel);

        setHostButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(null, "Enter host name:");
            Hosts host = new Hosts(name);
            host.enterPhrase();

            // update JLabel to show current host
            hostLabel.setText("Current host: " + host.getFirstName() + " " +  host.getLastName());
            frame.add(hostLabel);
        });


        // Create a JButton to add a new player
        JButton addPlayerButton = new JButton("Add Player");
        frame.add(addPlayerButton);
        addPlayerButton.setSize(30,30);
        addPlayerButton.setBounds(100, 100, 250, 100);
        addPlayerButton.addActionListener(e -> {
            // Prompt the user to enter a new player's name
            String firstName = JOptionPane.showInputDialog(frame, "Enter player name:");
            if (firstName != null && !firstName.isBlank()) {
                // Create a new Players object with the entered name
                Players newPlayer = new Players(firstName);

                // Add the new player to the currentPlayers array
                Players[] updatedPlayers = new Players[currentPlayers.length + 1];
                System.arraycopy(currentPlayers, 0, updatedPlayers, 0, currentPlayers.length);
                updatedPlayers[currentPlayers.length] = newPlayer;
                currentPlayers = updatedPlayers;

                // Update the players label to display the new player
                playersLabel.setText("Current players: " + getPlayerNames());


            }
        });


    }

    // Helper method to get the names of all players as a string
    private static String getPlayerNames() {
        StringBuilder sb = new StringBuilder();
        for (Players player : currentPlayers) {
            sb.append(player.getFirstName()).append(", ");
        }
        // Remove the trailing comma and space
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
