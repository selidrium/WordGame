import java.util.Scanner;

public class Hosts extends Person{

    public Hosts(String firstName) {
        super(firstName);
    }

    public Hosts(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void enterPhrase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a phrase for the players to guess:");
        String phrase = scanner.nextLine();
        Phrases.setGamePhrase(phrase);
    }

    public void playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {

            enterPhrase();
            System.out.println("New phrase entered. Let's play again!");
        } else {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }



}
