import java.util.Scanner;

public class Gameplay {

    private static Players player;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        new GUI();

        Hosts host = new Hosts("Alan", "Garcia");
//        host.randomizePhrase();
        Players[] currentPlayers = new Players[3];

        // Collect User Info
        for (int i = 0; i < currentPlayers.length; i++) {
            System.out.println("Please enter player " + (i + 1) + " name: ");
            String firstName = scan.nextLine();
            System.out.println("Enter your last name? Leave blank if you don't want it shown");
            String lastName = scan.nextLine();
            currentPlayers[i] = new Players(firstName);
        }


        // Game starts
        Turn turn = new Turn();
        int currentPlayerIndex = 0;
        boolean win = false;

        while (true) {
            win = turn.takeTurn(currentPlayers[currentPlayerIndex], host);
            currentPlayerIndex = (currentPlayerIndex + 1) % currentPlayers.length;

            if (win) {
                host.playAgain();
            }
        }

    }


}