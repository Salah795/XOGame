import java.util.Random;

/**
 *
 * @author Salah Mahmied.
 */
public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;

    private Random random;

    public CleverPlayer() {
        this.random = new Random();
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        for(int column = 0; column < DEFAULT_WIN_STREAK; column++) {
            if(board.putMark(mark, 0, column)) {
                return;
            }
            if(!board.getMark(0, column).equals(mark)) {
                break;
            }
        }
        int row = this.random.nextInt(board.getSize());
        int column = this.random.nextInt(board.getSize());
        if(!board.putMark(mark, row, column)) {
            playTurn(board, mark);
        }
    }
}
