import java.util.Random;

public class WhateverPlayer implements Player {
    private Random random;

    public WhateverPlayer() {
        this.random = new Random();
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        int chosenRow = this.random.nextInt(board.getSize());
        int chosenColumn = this.random.nextInt(board.getSize());
        if(!board.putMark(mark, chosenRow, chosenColumn)) {
            this.playTurn(board, mark);
        }
    }
}
