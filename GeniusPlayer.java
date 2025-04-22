/**
 * Class for defining a genius player objects that works in a special strategy for the XO game.
 * Also, it implements the Player interface and implements it's strategy on the method playTurn()
 * the GeniusPlayer strategy is to try to fill the diagonal of the coordinates (0,2), (1,1), (2,0)
 * with his mark, if the opponent have been filled one of them before the clever player
 * he will change his strategy and start to use the CleverPlayer strategy.
 * The goal of this strategy is to win at least 55% of the rounds in a XO game of 10,000 rounds
 * with boardSize = 4 and a winStreak = 3 against an object of type WhateverPlayer, and against
 * object of type CleverPlayer.
 *
 * @author Salah Mahmied.
 * @see WhateverPlayer
 * @see CleverPlayer
 * @see Player
 */
public class GeniusPlayer implements Player {
    private static final int DEFAULT_WIN_STREAK = 3;

    private CleverPlayer cleverPlayer;

    /**
     * The default constructor for the GeniusPlayer class
     * it will initialize the cleverPlayer object.
     */
    public GeniusPlayer() {
        this.cleverPlayer = new CleverPlayer();
    }

    /**
     * This method override from the Player interface it implements the GeniusPlayer strategy.
     * The strategy: to try to fill the diagonal of the coordinates (0,2), (1,1), (2,0)
     * with his mark, if the opponent have been filled one of them before the clever player
     * he will change his strategy and start to use the CleverPlayer strategy.
     * The goal of this strategy is to win at least 55% of the rounds in a XO game of 10,000 rounds
     * with boardSize = 4 and a winStreak = 3 against an object of type WhateverPlayer, and against
     * object of type CleverPlayer.
     * @param board The current state of the game board.
     * @param mark The mark of the genius player.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int row = 0;
        //Tries to fill the diagonal of the coordinates (0,2), (1,1), (2,0) with his mark
        for(int column = DEFAULT_WIN_STREAK - 1; column >= 0; column--) {
            if(!board.putMark(mark, row, column)) {
                if(!board.getMark(row, column).equals(mark)) {
                    //Changing strategy and starts to use the clever player strategy.
                    this.cleverPlayer.playTurn(board, mark);
                }
            } else {
                return;
            }
            row++;
        }
    }
}
