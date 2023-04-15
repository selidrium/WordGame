import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Turn {

    Scanner scan = new Scanner(System.in);
    public boolean takeTurn(Players player, Hosts host,JTextArea jTextArea,GUI gui) {

        jTextArea.append("\n\n"+player.getFirstName() + ", welcome to the game!\n");

        jTextArea.append( "The phrase to guess is: " + Phrases.getPlayingPhrase());
        jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
        while (true) {

            String guess=          JOptionPane.showInputDialog(host.getFirstName() + " says \"" + player.getFirstName() + ", enter your guess for a letter in my phrase\n\n");

            try {
                boolean letterFound = Phrases.findLetters(guess);
                boolean winsMoney = Math.random() < 0.5;
                Award award;
                if (winsMoney) {
                    award = new Money();
                } else {
                    award = new Physical();
                }

                if (letterFound) {

                    int winnings = award.displayWinnings(player, true);
                    if (award instanceof Money) {
                        player.addMoney(1000);
                        System.out.println(winnings);
                    } else {
                        int prizeIndex = ((Physical) award).displayWinnings(player, true);
                        String prize = ((Physical) award).getPrizeAtIndex(prizeIndex);

                        jTextArea.append("\n\n");
                        jTextArea.append(""+player.getFirstName()+", yes, that letter is in the phrase! You won a "+prize+"!\n");
                        jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
                    }


                    boolean isWin = Phrases.checkWin();
                    return isWin;
                } else {
                    int winnings = award.displayWinnings(player, false);
                    player.addMoney(-500);

                    jTextArea.append("\n%s, no, that letter is not in the phrase!\n");
                    jTextArea.append(winnings+"");
                    jTextArea.append(player.toString()+"\n\n");
                    jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
                }
            } catch (MultipleLettersException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return false;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,"Please Enter A number ");

                return false;
            }
        }
    }

}
