import java.util.Scanner;

public class Turn {
    private int winAmount = 100;
    private int lossAmount = 50;

    public boolean takeTurn (Players player, Hosts host) {
        Scanner scan = new Scanner(System.in);
        System.out.println(host.toString() + " says, " + player.getFirstName() + " " + player.getLastName() + " enter your guess for my random number between 0 and 100");
        int numToGuess = scan.nextInt();
        boolean win = Numbers.compareNumber(numToGuess);

        if (win) {
            System.out.println("Congratulations! You guessed the number!");
            System.out.println("+$" + winAmount);
            player.setAmountOfMoney(player.getAmountOfMoney() + winAmount);
            System.out.println(player.toString());
            return true;
        } else {
            System.out.println("-$" + lossAmount);
            player.setAmountOfMoney(player.getAmountOfMoney() - lossAmount);
            System.out.println(player.toString());
            return false;
        }



    }



}
