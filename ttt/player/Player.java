package ttt.player;

import ttt.main.Board;
import ttt.main.Mark;

/**
 * Interface that defines a player in the XO game.
 * This interface should be implemented by all the classes of all
 * the types of the players (ttt.player.HumanPlayer, ttt.player.WhateverPlayer, ttt.player.CleverPlayer, ttt.player.GeniusPlayer).
 * It's having one method playTurn() that should be implemented in all the player types
 * class. it receives the game board of type ttt.main.Board and the player mark of type ttt.main.Mark
 * and lets the player play his turn.
 *
 * @author Salah Mahmied
 * @see Board
 * @see Mark
 */
public interface Player {
    /**
     * This method should be implemented in all the player types
     * class. It receives the game board of type ttt.main.Board and the player mark of type ttt.main.Mark
     * and lets the player play his turn.
     * @param board The game board current state.
     * @param mark The player mark.
     */
    void playTurn(Board board, Mark mark);
}