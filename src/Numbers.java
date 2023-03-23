import java.util.Random;

public class Numbers {

    private static int  randomNum;

    // Setters and Getters
    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        Numbers.randomNum = randomNum;
    }

    // Methods
    public void generateNumber() {
        Random rand = new Random();
        randomNum = rand.nextInt(101);
    }

    public boolean compareNumber(int guess) {
        if (guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        }
        else if ( guess > randomNum) {
            System.out.println("I'm sorry.  That guess was too high.");
            return false;
        }
        else {
            System.out.println("I'm sorry, That guess was too low.");
            return false;
        }

    }



}
