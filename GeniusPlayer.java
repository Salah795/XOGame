public class GeniusPlayer implements Player {

    private CleverPlayer cleverPlayer;

    public GeniusPlayer() {
        this.cleverPlayer = new CleverPlayer();
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        for(int row = 0; row < 3; row++) {
            for(int column = Game.DEFAULT_WIN_STREAK - 1; column >= 0; column--) {
                if(!board.putMark(mark, row, column)) {
                    if(!board.getMark(row, column).equals(mark)) {
                        this.cleverPlayer.playTurn(board, mark);
                    }
                }
            }
        }
    }
}
