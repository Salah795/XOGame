import java.util.Random;

/**
 * Class for defining the whatever player objects that works in a special strategy for the XO game.
 * Also, it implements the Player interface and implements it's strategy on the method playTurn()
 * the WhateverPlayer strategy is to choose the coordinates randomly and tries to put his mark
 * in the game board in these coordinates, and if it didn't succeed it will try again with different
 * coordinates that it will also choose randomly.
 * @see Player
 */
public class WhateverPlayer implements Player {
    private Random random;

    /**
     * The default constructor for the WhateverPlayer class
     * it will initialize the random property.
     */
    public WhateverPlayer() {
        this.random = new Random();
    }

    /**
     * This method override from the Player interface it implements the WhateverPlayer strategy.
     * The strategy: is to choose the coordinates randomly and tries to put his mark
     * in the game board in these coordinates, and if it didn't succeed it will try again with different
     * coordinates that it will also choose randomly.
     * @param board The current state of the game board.
     * @param mark The mark of the WhateverPlayer.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        //Choose the points randomly.
        int chosenRow = this.random.nextInt(board.getSize());
        int chosenColumn = this.random.nextInt(board.getSize());
        if(!board.putMark(mark, chosenRow, chosenColumn)) {
            this.playTurn(board, mark);
        }
    }
}
