public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;
    private static final int COORDINATE_LENGTH = 2;

    private int[][] chosenCoordinates;
    public CleverPlayer() {
        this.chosenCoordinates = new int[DEFAULT_WIN_STREAK][];
        for (int index = 0; index < DEFAULT_WIN_STREAK; index++) {
            this.chosenCoordinates[index] = new int[COORDINATE_LENGTH];
        }
    }

    @Override
    public void playTurn(Board board, Mark mark) {

    }
}
