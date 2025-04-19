import java.util.Random;

public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;
    private static final int COORDINATE_LENGTH = 2;

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
        switch(this.currentStreakLength) {
            case 0:
                int chosenRow = this.random.nextInt(board.getSize());
                int chosenColumn = this.random.nextInt(board.getSize());
                if(!board.putMark(mark, chosenRow, chosenColumn)) {
                    this.playTurn(board, mark);
                }
                this.chosenCoordinates[this.currentStreakLength] = new int[]{chosenRow, chosenColumn};
                this.currentStreakLength++;
                break;
            case 1:
                if(this.chosenCoordinates[0][1] == 0) {

                }
        }
    }
}
