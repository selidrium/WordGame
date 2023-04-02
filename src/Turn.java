import java.util.Scanner;

public class Turn {
    private Scanner scan = new Scanner(System.in);


    public boolean takeTurn(Players player, Hosts host) {
        System.out.printf("%s, please guess a number between 0 and 100: ", player.getFirstName());
        int guess = scan.nextInt();
        boolean win = Numbers.compareNumber(guess);
        boolean awardMoney = Math.random() < 0.5;

        if (awardMoney) {
            Money moneyAward = new Money();
            int moneyAmount = moneyAward.displayWinnings(player, win);
            player.setAmountOfMoney(player.getAmountOfMoney() + moneyAmount);
        } else {
            Physical physicalAward = new Physical();
            int prizeIndex = physicalAward.getRandomPrize();
            int prizeAmount = physicalAward.displayWinnings(player, win);
            player.setAmountOfMoney(player.getAmountOfMoney() + prizeAmount);
        }

        System.out.println(player.toString());

        return win;
    }
}