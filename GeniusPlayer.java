public class GeniusPlayer implements Player {

    private CleverPlayer cleverPlayer;
    private boolean useCleverPlayer;
    private int chosenRow;
    private int chosenColumn;

    public GeniusPlayer() {
        this.cleverPlayer = new CleverPlayer();
        this.useCleverPlayer = false;
        this.chosenRow = 0;
        this.chosenColumn = Game.DEFAULT_WIN_STREAK - 1;
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        if(!this.useCleverPlayer) {
            if(!board.putMark(mark, this.chosenRow, this.chosenColumn)) {
                this.useCleverPlayer = true;
                this.playTurn(board, mark);
            } else {
                this.chosenRow++;
                this.chosenColumn--;
            }
        } else {
            this.cleverPlayer.playTurn(board, mark);
        }
    }
}
