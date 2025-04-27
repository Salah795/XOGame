package ttt.Rendering;

import ttt.main.Board;

/**
 * Interface that defines the renderer for the XO game.
 * This interface should be implemented by all the classes of all
 * the types of the renderer (ttt.Rendering.VoidRenderer, ttt.Rendering.ConsoleRenderer).
 * It's having one method renderBoard() that should be implemented in all the renderer types
 * class. it receives the game board of type ttt.main.Board and implements the renderer type.
 *
 * @author Salah Mahmied
 * @see Board
 */
public interface Renderer {
    /**
     * This method should be implemented in all the renderer types class.
     * It receives the game board of type ttt.main.Board and implements the renderer type.
     * For example: if the renderer type is console it will print the current game board
     * state after every turn in all the rounds.
     * @param board The game board current state.
     */
    void renderBoard(Board board);
}