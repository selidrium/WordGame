public class Physical implements Award {
    private String[] prizes = {"Trip to Hawaii", "New car", "Flat screen TV", "Gourmet cooking set", "Designer handbag"};

    public int getRandomPrize() {
        return (int) (Math.random() * prizes.length);
    }

    public int displayWinnings(Players player, boolean isWinner) {
        if (isWinner) {
            int prizeIndex = getRandomPrize();
            System.out.printf("%s, you won a %s!\n", player.getFirstName(), prizes[prizeIndex]);
            return 0;
        } else {
            int prizeIndex = getRandomPrize();
            System.out.printf("%s, you lost. You could have won a %s.\n", player.getFirstName(), prizes[prizeIndex]);
            return 0;
        }
    }
}