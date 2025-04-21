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

    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.gameBoard = new Board();
        this.winStreak = DEFAULT_WIN_STREAK;
    }

    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.gameBoard = new Board(size);
        this.winStreak = winStreak;
    }

    public int getWinStreak() {
        return this.winStreak;
    }

    public int getBoardSize() {
        return this.gameBoard.getSize();
    }

    public Mark run() {
        for(int turnsCounter = 0; turnsCounter < (this.getBoardSize() * this.getBoardSize()) / PLAYERS_NUMBER;
            turnsCounter++) {
            this.playerX.playTurn(this.gameBoard, Mark.X);
            this.renderer.renderBoard(this.gameBoard);
            if(checkVerticalOrHorizontal(Mark.X, true) ||
                    checkVerticalOrHorizontal(Mark.X, false)
                    || checkDiagonallyDownOrUp(Mark.X, DIAGONAL_DOWN_DIRECTION) ||
                    checkDiagonallyDownOrUp(Mark.X, DIAGONAL_UP_DIRECTION)) {
                return Mark.X;
            }
            this.playerO.playTurn(this.gameBoard, Mark.O);
            this.renderer.renderBoard(this.gameBoard);
            if(checkVerticalOrHorizontal(Mark.O, true) ||
                    checkVerticalOrHorizontal(Mark.O, false)
                    || checkDiagonallyDownOrUp(Mark.O, DIAGONAL_DOWN_DIRECTION) ||
                    checkDiagonallyDownOrUp(Mark.O, DIAGONAL_UP_DIRECTION)) {
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

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
