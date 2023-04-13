import java.util.Scanner;

public class Turn {
//    Money money = new Money();
//    Physical physical = new Physical();
    Scanner scan = new Scanner(System.in);
    public boolean takeTurn(Players player, Hosts host) {

        System.out.println(player.getFirstName() + ", welcome to the game!");
        System.out.println("The phrase to guess is: " + Phrases.getPlayingPhrase());

        while (true) {
            System.out.println(host.getFirstName() + " says \"" + player.getFirstName() + ", enter your guess for a letter in my phrase\"");
            String guess = scan.nextLine().toLowerCase();

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
//                        winnings = ((Money) award).displayWinnings(player, true);
                        player.addMoney(1000);
                        System.out.println(winnings);
                    } else {
                        int prizeIndex = ((Physical) award).displayWinnings(player, true);
                        String prize = ((Physical) award).getPrizeAtIndex(prizeIndex);
                        System.out.printf("%s, yes, that letter is in the phrase! You won a %s!\n", player.getFirstName(), prize);
//                        System.out.println(winnings);
                    }


                    boolean isWin = Phrases.checkWin();
                    return isWin;
                } else {
                    int winnings = award.displayWinnings(player, false);
                    player.addMoney(-500);
                    System.out.printf("%s, no, that letter is not in the phrase!");
                    System.out.println(winnings);
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
