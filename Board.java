/**
 * Class for defining a board with a specific size (which is a squared matrix) for the XO game for two
 * players.
 * The content of each point in the board matrix will be BLANK, X, or O from the Mark enum.
 *
 * @author Salah Mahmied
 * @see Mark
 */
public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4;

    private Mark[][] boardMatrix;
    private int boardSize;

    /**
     * The default constructor for the Board class that
     * defines the board matrix with the default board size which is 4,
     * and creating the board matrix by calling the createEmptyBoard() private method.
     */
    public Board() {
        this.boardSize = DEFAULT_BOARD_SIZE;
        createEmptyBoard(this.boardSize);
    }

    /**
     * The constructor that defines the board with a custom size.
     * It receives the wanted board size as a parameter and
     * creates the board matrix with that size by calling
     * the createEmptyBoard() private method.
     * @param size The wanted board size.
     */
    public Board(int size) {
        this.boardSize = size;
        createEmptyBoard(this.boardSize);
    }

    /**
     * Gets the size, which will be the number of rows or columns
     * (they are equal because that the matrix is squared) in the board matrix of the board.
     * @return the board size.
     */
    public int getSize() {
        return this.boardSize;
    }

    /**
     * This method tries to put a given mark of type Mark which will
     * be Mark.X, Mark.O, Mark.BLANK in a certain point in the
     * board matrix with coordinate (row, col).
     * It will check first if the given coordinates are legal,
     * then it will check if the mark in the point with the given coordinates
     * in the matrix have the value BLANK by calling the getMark() method.
     * if both the two conditions are true it will put the given mark in the point with the given coordinates
     * and return true, else it will return false without changing anything in the board matrix.
     * @param mark The given mark to try to put in the point with the given coordinates in the board matrix.
     * @param row The row index for the chosen point in the board matrix.
     * @param col The column index for the chosen point in the board matrix.
     * @return True if the given coordinates are legal and the mark in the point with the given coordinates
     * in the matrix have the value BLANK. else it will return false.
     */
    public boolean putMark(Mark mark, int row, int col) {
        //Checks if the row index of the chosen point exist in the valid range.
        if (row < 0 || row >= this.boardSize) {
            return false;
        }
        //Checks if the row index of the chosen point exist in the valid range.
        if (col < 0 || col >= this.boardSize) {
            return false;
        }
        //Check if the mark in the point with the given coordinates in the matrix have the value BLANK.
        if (!this.getMark(row, col).equals(Mark.BLANK)) {
            return false;
        }
        //Put the given mark in the point with the given coordinates
        this.boardMatrix[row][col] = mark;
        return true;
    }

    /**
     * Gets the Mark of the point with the given coordinates in the matrix.
     * It will check first if the given coordinates are legal and if so it
     * will return the mark else it will return BLANK.
     * @param row The row index for the chosen point in the board matrix.
     * @param col The column index for the chosen point in the board matrix.
     * @return The mark of the point with the given coordinates in the board matrix
     * if the given coordinates are legal, else it will return BLANK.
     */
    public Mark getMark(int row, int col) {
        //Checks if the row and the column indexes of the chosen point exist in the valid range.
        if (row < 0 || row >= this.boardSize || col < 0 || col >= this.boardSize) {
            return Mark.BLANK;
        }
        return this.boardMatrix[row][col];
    }

    /**
     * Creates an empty squared board matrix with a given size
     * and puts the BLANK mark in all the points of the matrix.
     * @param boardSize The wanted board size.
     */
    private void createEmptyBoard(int boardSize) {
        this.boardMatrix = new Mark[boardSize][];
        for (int row = 0; row < boardSize; row++) {
            this.boardMatrix[row] = new Mark[boardSize];
            for (int column = 0; column < boardSize; column++) {
                this.boardMatrix[row][column] = Mark.BLANK;
            }
        }
    }
}
