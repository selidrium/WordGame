public class Money implements Award {
    private int winningAmount;
    private int losingAmount;

    public Money(int winningAmount, int losingAmount) {
        this.winningAmount = winningAmount;
        this.losingAmount = losingAmount;
    }

    public int displayWinnings(Players player, boolean isCorrect) {
        int amount = isCorrect ? winningAmount : losingAmount;
        if (isCorrect) {
            System.out.println(player.getFirstName() + " won $" + winningAmount);
        } else {
            System.out.println(player.getFirstName() + " lost $" + losingAmount);
        }
        return amount;
    }
}