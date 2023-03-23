import java.util.Scanner;

public class Gameplay {

    private static Players player;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        Hosts host = new Hosts("Alan", "Garcia");
        host.randomizeNum();

        // Collect User Info
        System.out.println("What is your first name?");
        String firstName = scan.next();
        System.out.println("You are entering a last name? (y/n)");
        String answer = scan.next().toLowerCase();

        if (answer.equals("y")){
            System.out.println("Enter your last name");
            String lastName = scan.next();
            player = new Players(firstName, lastName);
        } else {
            player = new Players(firstName);
        }

        // Game starts
        Turn turn = new Turn();
        boolean win = false;

        while (true) {
            win = turn.takeTurn(player, host);

            if (win) {
                System.out.println("Would you like to play again? (Y/N)");
                String choice = scan.next().toLowerCase();
                if (choice.equals("y")) {
                    host.randomizeNum();
                    win = false;
                }
                else {
                    System.out.println("Thanks for playing");
                    break;
                }
            }
        }

    }


}