import java.util.Random;

/**
 * Class for defining a clever player objects that works in a special strategy for the XO game.
 * Also, it implements the Player interface and implements it's strategy on the method playTurn()
 * the CleverPlayer strategy is to try to fill the first three points in the board matrix with his mark
 * and if the opponent have been filled one of them before the clever player he will change
 * his strategy and start to choose the points randomly, and it didn't succeed it will
 * try again to choose another coordinates randomly.
 * The goal of this strategy is to win at least 55% of the rounds in a XO game of 10,000 rounds
 * with boardSize = 4 and a winStreak = 3 against an object of type WhateverPlayer.
 *
 * @author Salah Mahmied.
 * @see WhateverPlayer
 * @see Player
 */
public class CleverPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;

    private Random random;

    /**
     * The default constructor for the CleverPlayer class
     * it will initialize the random property.
     */
    public CleverPlayer() {
        this.random = new Random();
    }

    /**
     * This method override from the Player interface it implements the CleverPlayer strategy.
     * The strategy: to try to fill the first three points in the board matrix with his mark
     * and if the opponent have been filled one of them before the clever player he will change
     * his strategy and start to choose the points randomly, and it didn't succeed it will
     * try again to choose another coordinates randomly.
     * The goal of this strategy is to win at least 55% of the rounds in a XO game of 10,000 rounds
     * with boardSize = 4 and a winStreak = 3 against an object of type WhateverPlayer.
     * @param board The current state of the game board.
     * @param mark The mark of the clever player.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        //Tries to fill the first three points in the board matrix with his mark.
        for(int column = 0; column < DEFAULT_WIN_STREAK; column++) {
            if(board.putMark(mark, 0, column)) {
                return;
            }
            if(!board.getMark(0, column).equals(mark)) {
                break;
            }
        }
        //Changed the strategy and starts to choose the points randomly.
        int row = this.random.nextInt(board.getSize());
        int column = this.random.nextInt(board.getSize());
        if(!board.putMark(mark, row, column)) {
            playTurn(board, mark);
        }
    }
}
