public class Physical implements Award {
    private String[] prizes;

    public Physical(String[] prizes) {
        this.prizes = prizes;
    }

    public int getRandomPrize() {
        return (int) (Math.random() * prizes.length);
    }

    public int displayWinnings(Players player, boolean isCorrect) {
        int prizeIndex = getRandomPrize();
        if (isCorrect) {
            System.out.println(player.getFirstName() + " won a " + prizes[prizeIndex]);
        } else {
            System.out.println(player.getFirstName() + " lost. They could have won a " + prizes[prizeIndex]);
        }
        return 0;
    }
}