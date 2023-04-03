import java.util.Random;

public class Phrases {

    private static int  randomNum;
    private static String gamePhrase;
    private static String playingPhrase;



    // Setters and Getters
    public int getRandomNum() {
        return randomNum;
    }

    public static void setRandomNum() {
        Random rand = new Random();
        randomNum = rand.nextInt(100);
    }


    public static void setGamePhrase(String phrase) {
        gamePhrase = phrase;
        // initialize playingPhrase with underlines for each letter
        playingPhrase = gamePhrase.replaceAll("[A-Za-z0-9]", "_");
    }

    public static String getPlayingPhrase() {
        return playingPhrase;
    }

    // Methods


    public static boolean findLetters(String letter) throws MultipleLettersException {
        if (letter.length() != 1) {
            throw new MultipleLettersException();
        }

        for (int i = 0; i < gamePhrase.length(); i++) {
            if (gamePhrase.charAt(i) == letter.charAt(0)) {
                playingPhrase = playingPhrase.substring(0, i) + letter + playingPhrase.substring(i+1);
            }
        }

        if (playingPhrase.contains(letter)) {
            return true;
        }

        return false;
    }

    public static boolean checkWin() {
        if (!playingPhrase.contains("_")) {
            System.out.println("Congratulations, you have won the game!");
            return true;
        }
        return false;
    }



}