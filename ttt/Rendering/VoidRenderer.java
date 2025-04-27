package ttt.Rendering;

import ttt.main.Board;

/**
 * Class that defines the renderer of type void which will
 * not print the game board state in any stage of the game.
 * It implements the Renderer interface and will implement it's method rendererBoard()
 * that will do nothing.
 *
 * @author Salah Mahmied.
 * @see Renderer
 */
public class VoidRenderer implements Renderer {
    /**
     * This method don't do anything, it's part of the Renderer interface
     * because this class is of type void, so the void renderer shouldn't print anything
     * for that we make its implementation empty.
     * @param board The game board.
     */
    @Override
    public void renderBoard(Board board) {}
}
