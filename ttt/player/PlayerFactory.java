package ttt.player;

/**
 * Class for defining the player factory which helps to
 * decide the player that should be created based on the received string.
 * It's implementing the Single Responsibility Principle (DIP).
 *
 * @author Salah Mahmied.
 */
public class PlayerFactory {
    /**
     * The default constructor for the ttt.player.PlayerFactory.
     */
    public PlayerFactory() {}

    /**
     * This method will create the chosen player.
     * @param type The string that represents the type of the chosen player.
     * @return The ttt.player.Player object that have been created based on the type parameter if the
     * type is legal, else it will return null.
     */
    public Player buildPlayer(String type) {
        switch (type) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "genius":
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}
