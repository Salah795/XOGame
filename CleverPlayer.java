import java.util.Random;

public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;

    private int[][] chosenCoordinates;
    private int currentStreakLength;
    private Random random;

    public CleverPlayer() {
        this.random = new Random();
        this.currentStreakLength = 0;
        this.chosenCoordinates = new int[DEFAULT_WIN_STREAK][];
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        int chosenRow = this.random.nextInt(board.getSize());
        int chosenColumn = this.currentStreakLength;
        if(!board.putMark(mark, chosenRow, chosenColumn)) {
            this.currentStreakLength = 0;
            this.playTurn(board, mark);
        }
        if(this.currentStreakLength < DEFAULT_WIN_STREAK) {
            this.chosenCoordinates[this.currentStreakLength] = new int[]{chosenRow, chosenColumn};
        }
        this.currentStreakLength++;
    }
}
