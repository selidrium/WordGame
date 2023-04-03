import java.util.Scanner;

public class Turn {
    Money money = new Money();
    Scanner scan = new Scanner(System.in);
    public boolean takeTurn(Players player, Hosts host) {


        System.out.println(player.getFirstName() + ", welcome to the game!");
        System.out.println("The phrase to guess is: " + Phrases.getPlayingPhrase());

        while (true) {
            System.out.println(host.getFirstName() + " says \"" + player.getFirstName() + ", enter your guess for a letter in my phrase\"");
            String guess = scan.nextLine().toLowerCase();


            try {
                boolean letterFound = Phrases.findLetters(guess);
                if (letterFound) {
                    int winnings = money.displayWinnings(player, true);
                    player.addMoney(winnings);
                    System.out.printf("%s, yes, that letter is in the phrase! You won $%d\n", player.getFirstName(), winnings);
                    System.out.println(player.toString());
                    boolean isWin = Phrases.checkWin();
                    return isWin;
                } else {
                    int losses = money.displayWinnings(player, false);
                    player.addMoney(losses);
                    System.out.printf("%s, no, that letter is not in the phrase! You lost $%d\n", player.getFirstName(), losses);
                    System.out.println(player.toString());
                }
            } catch (MultipleLettersException e) {
                System.out.println(e.getMessage());
                return false;
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a letter.");
                return false;
            }
        }
    }



}
