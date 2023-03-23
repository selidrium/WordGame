import java.util.Random;

public class Numbers {

    private static int  randomNum;

    // Setters and Getters
    public int getRandomNum() {
        return randomNum;
    }

    public static void setRandomNum() {
        Random rand = new Random();
        randomNum = rand.nextInt(100);
    }

    // Methods
    public void generateNumber() {
        Random rand = new Random();
        randomNum = rand.nextInt(101);
    }

    public static boolean compareNumber(int guess) {
        if (guess == randomNum) {
            return true;
        }
        else if ( guess > randomNum) {
            System.out.println("I'm sorry, that guess was too high.");
            return false;
        }
        else {
            System.out.println("I'm sorry, that guess was too low.");
            return false;
        }

    }



}
