/**
 * Class for defining the XO game objects with a board of a specific size (which is a squared matrix)
 * for two players.
 * The content of each point in the board matrix will be BLANK, X, or O from the Mark enum.
 *
 * @author Salah Mahmied
 * @see Board
 * @see Mark
 */
public class Game {
    private static final int DEFAULT_WIN_STREAK = 3;
    private static final int DIAGONAL_DOWN_DIRECTION = -1;
    private static final int DIAGONAL_UP_DIRECTION = 1;
    private static final int PLAYERS_NUMBER = 2;

    private Board gameBoard;
    private int winStreak;
    private Player playerX;
    private Player playerO;
    private Renderer renderer;

    /**
     * The constructor for defining a game with board of size 4 (the default board size)
     * and win streak 3 (the default win streak), and receiving the two players
     * and the renderer as parameters.
     * @param playerX The player that have the mark X.
     * @param playerO The player that have the mark O.
     * @param renderer The renderer of the game (console for printing the board state after each
     *                 turn/void without printing the board in any stage.)
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.gameBoard = new Board();
        this.winStreak = DEFAULT_WIN_STREAK;
    }

    /**
     * The constructor for defining a game, with receiving the board size, win streak, the two players
     * the renderer as parameters.
     * @param playerX The player that have the mark X.
     * @param playerO The player that have the mark O.
     * @param size The game board size.
     * @param winStreak The win streak.
     * @param renderer The renderer of the game (console for printing the board state after each
     *                 turn/void without printing the board in any stage.)
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.gameBoard = new Board(size);
        this.winStreak = winStreak;
    }

    /**
     * Gets the win streak.
     * @return The win streak.
     */
    public int getWinStreak() {
        return this.winStreak;
    }

    /**
     * Gets the game board size.
     * @return The game board size.
     */
    public int getBoardSize() {
        return this.gameBoard.getSize();
    }

    /**
     * This method will run the game for one round between playerX and playerO.
     * If one of the players win it will return his mark, else it will return BLANK.
     * @return The mark of the winner if one of the players win the round, else it will return BLANK.
     */
    public Mark run() {
        //This loop will run as the number of the points in the board matrix which is
        //this.getBoardSize() * this.getBoardSize() and decided it with the number of players.
        //Because in every iteration all the players will play their turn.
        for(int turnsCounter = 0; turnsCounter < (this.getBoardSize() * this.getBoardSize()) / PLAYERS_NUMBER;
            turnsCounter++) {
            //playerX will play his turn with mark X.
            this.playerX.playTurn(this.gameBoard, Mark.X);
            //prints the current board state if the renderer is console.
            this.renderer.renderBoard(this.gameBoard);
            //Checks if playerX wins the round.
            if(checkVerticalOrHorizontal(Mark.X, true) ||
                    checkVerticalOrHorizontal(Mark.X, false)
                    || checkDiagonallyDownOrUp(Mark.X, DIAGONAL_DOWN_DIRECTION) ||
                    checkDiagonallyDownOrUp(Mark.X, DIAGONAL_UP_DIRECTION)) {
                return Mark.X;
            }

            //playerO will play his turn with mark O.
            this.playerO.playTurn(this.gameBoard, Mark.O);
            //prints the current board state if the renderer is console.
            this.renderer.renderBoard(this.gameBoard);
            //Checks if playerX wins the round.
            if(checkVerticalOrHorizontal(Mark.O, true) ||
                    checkVerticalOrHorizontal(Mark.O, false)
                    || checkDiagonallyDownOrUp(Mark.O, DIAGONAL_DOWN_DIRECTION) ||
                    checkDiagonallyDownOrUp(Mark.O, DIAGONAL_UP_DIRECTION)) {
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

    /**
     * This method have two options of checking (vertical/horizontal), it will check if the received
     * mark have been reached the win streak.
     * If the boolean parameter vertical is true it will check vertically else
     * it will check horizontally.
     * @param mark The mark for check.
     * @param vertical The boolean variable for deciding if to check vertically or horizontally.
     * @return true if the given mark have been reached the win streak, else false.
     */
    private boolean checkVerticalOrHorizontal(Mark mark, boolean vertical) {
        int row;
        int column;
        for(int outerIndex = 0; outerIndex < this.getBoardSize(); outerIndex++) {
            int currentStreak = 0;
            for(int innerIndex = 0; innerIndex < this.getBoardSize(); innerIndex++) {
                if(vertical) {
                    row = innerIndex;
                    column = outerIndex;
                } else {
                    row = outerIndex;
                    column = innerIndex;
                }
                if(this.gameBoard.getMark(row, column).equals(mark)) {
                    currentStreak++;
                }
            }
            if(currentStreak >= this.winStreak) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method have two options of checking (diagonally from up to down / diagonally from down to up)
     * it will check if the received mark have been reached the win streak diagonally.
     * If the parameter direction is +1 it will check diagonally from up to down else
     * If the parameter direction is -1 it will check diagonally from down to up.
     * @param mark The mark for check.
     * @param direction The direction parameter for deciding to check diagonally from up to down
     *                  or diagonally from down to up, it's value should be always -1 or +1.
     * @return true if the given mark have been reached the win streak diagonally, else false.
     */
    private boolean checkDiagonallyDownOrUp(Mark mark, int direction) {
        Mark[] coordinatesList = new Mark[this.getBoardSize() * this.getBoardSize()];
        int filledCoordinatesCounter = 0;
        for(int row = 0; row < this.getBoardSize(); row++) {
            for(int column = 0; column < this.getBoardSize(); column++) {
                coordinatesList[filledCoordinatesCounter] = this.gameBoard.getMark(row, column);
                filledCoordinatesCounter++;
            }
        }
        for(int index = 0; index < coordinatesList.length; index++) {
            int currentStreak = 0;
            for(int diagonalIndex = index; diagonalIndex < coordinatesList.length;
                diagonalIndex += this.getBoardSize() + direction){
                if(coordinatesList[diagonalIndex].equals(mark)){
                    currentStreak++;
                }
            }
            if(currentStreak >= this.winStreak) {
                return true;
            }
        }
        return false;
    }
}
