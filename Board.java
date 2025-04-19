public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4;

    private Mark[][] boardState;
    private int size;

    public Board() {
        this.size = DEFAULT_BOARD_SIZE;
        createEmptyBoard();
    }

    public Board(int size) {
        this.size = size;
        createEmptyBoard();
    }

    public int getSize() {
        return this.size;
    }

    public boolean putMark(Mark mark, int row, int col) {
        //TODO check if we really need to check the validity of the row and col values.
        if (row < 0 || row >= this.size) {
            return false;
        }
        if (col < 0 || col >= this.size) {
            return false;
        }
        //TODO check if need to use '==' operator or equals() method .
        if (!this.getMark(row, col).equals(Mark.BLANK)) {
            return false;
        }
        this.boardState[row][col] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {
        if (row < 0 || row >= this.size || col < 0 || col >= this.size) {
            return Mark.BLANK;
        }
        return this.boardState[row][col];
    }

    private void createEmptyBoard() {
        this.boardState = new Mark[this.size][];
        for (int row = 0; row < this.size; row++) {
            this.boardState[row] = new Mark[this.size];
            for (int column = 0; column < this.size; column++) {
                this.boardState[row][column] = Mark.BLANK;
            }
        }
    }
}
