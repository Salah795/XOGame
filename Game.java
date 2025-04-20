public class Game {
    //TODO check if it's legal for this exercise to make final variables public.
    public static final int DEFAULT_WIN_STREAK = 3;

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
        this.playerX.playTurn(this.gameBoard, Mark.X);

    }

    private boolean checkVerticalOrHorizontal(Mark mark) {
        int outerIndex = 0;
        int innerIndex = 0;
        for(; outerIndex < this.getBoardSize(); outerIndex++) {
            int currentStreak = 0;
            for(int row = 0; row < this.getBoardSize(); row++) {
                if(this.gameBoard.getMark(row, outerIndex).equals(mark)) {
                    currentStreak++;
                }
            }
            if(currentStreak == this.winStreak) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal(Mark mark) {
        for(int row = 0; row < this.getBoardSize(); row++) {
            int currentStreak = 0;
            for(int column = 0; column < this.getBoardSize(); column++) {
                if(this.gameBoard.getMark(row, column).equals(mark)) {
                    currentStreak++;
                }
                if(currentStreak == this.winStreak) {
                    return true;
                }
            }
        }
    }
}
