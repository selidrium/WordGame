import java.util.Scanner;

public class Gameplay {

    private static Person person;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // Collect User Info
        System.out.println("What is your first name?");
        String firstName = scan.next();
        System.out.println("You are entering a last name? (y/n)");
        String answer = scan.next().toLowerCase();
        if (answer.equals("y")){
            System.out.println("Enter your last name");
            String lastName = scan.next();
            person = new Person(firstName, lastName);
        } else {
            person = new Person(firstName);
        }

        // Game starts
        Numbers game = new Numbers();
        game.generateNumber();
        boolean guessed = false;
        while ( !guessed ) {
            System.out.println(person.getFirstName() + person.getLastName() +", Guess a number between 1 - 100");
            int guess = scan.nextInt();
            if (game.compareNumber(guess)) {
                guessed = true;
            }

        }

    }
}