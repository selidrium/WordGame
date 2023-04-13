public interface Award {
    public int displayWinnings(Players player, boolean isCorrect);



    public class Money implements Award {
        @Override
        public int displayWinnings(Players player, boolean isCorrect) {
            return 0;
        }
        // implementation of displayWinnings() method
    }

    public class Physical implements Award {
        @Override
        public int displayWinnings(Players player, boolean isCorrect) {
            return 0;
        }
        // implementation of displayWinnings() method
    }
}