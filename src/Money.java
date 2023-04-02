public class Money implements Award {
    private int winAmount = 100;
    private int loseAmount = -50;

    public int displayWinnings(Players player, boolean isWinner) {
        if (isWinner) {
            System.out.printf("%s, you won $%d!\n", player.getFirstName(), winAmount);
            return winAmount;
        } else {
            System.out.printf("%s, you lost $%d.\n", player.getFirstName(), Math.abs(loseAmount));
            return loseAmount;
        }
    }
}