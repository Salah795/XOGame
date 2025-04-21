public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4;

    private Mark[][] boardState;
    private int boardSize;

    public Board() {
        this.boardSize = DEFAULT_BOARD_SIZE;
        createEmptyBoard();
    }

    public Board(int size) {
        this.boardSize = size;
        createEmptyBoard();
    }

    public int getSize() {
        return this.boardSize;
    }

    public boolean putMark(Mark mark, int row, int col) {
        if (row < 0 || row >= this.boardSize) {
            return false;
        }
        if (col < 0 || col >= this.boardSize) {
            return false;
        }
        if (!this.getMark(row, col).equals(Mark.BLANK)) {
            return false;
        }
        this.boardState[row][col] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {
        if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
            return Mark.BLANK;
        }
        return this.boardState[row][col];
    }

    private void createEmptyBoard() {
        this.boardState = new Mark[this.boardSize][];
        for (int row = 0; row < this.boardSize; row++) {
            this.boardState[row] = new Mark[this.boardSize];
            for (int column = 0; column < this.boardSize; column++) {
                this.boardState[row][column] = Mark.BLANK;
            }
        }
    }
}
