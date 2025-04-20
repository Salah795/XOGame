import java.util.Random;

public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;

    private int[][] chosenCoordinates;
    private int currentStreakLength;
    private Random random;
    private int occupiedCoordinatesInFirstColumn;

    public CleverPlayer() {
        this.random = new Random();
        this.currentStreakLength = 0;
        this.chosenCoordinates = new int[DEFAULT_WIN_STREAK][];
        this.occupiedCoordinatesInFirstColumn = 0;
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        //TODO check this function again.
        int chosenRow = this.occupiedCoordinatesInFirstColumn;
        int chosenColumn = this.currentStreakLength;
        if(this.currentStreakLength == 0 && this.occupiedCoordinatesInFirstColumn < board.getSize()) {
            this.occupiedCoordinatesInFirstColumn++;
        }
        if(this.occupiedCoordinatesInFirstColumn >= board.getSize()) {
            chosenRow = random.nextInt(board.getSize());
            chosenColumn = random.nextInt(board.getSize());
        }
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
