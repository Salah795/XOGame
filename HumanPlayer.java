/**
 * Class for defining a human player objects the XO game, by interacting with the user
 * and receiving his chosen coordinates from the console.
 * Also, it implements the Player interface and implements the interaction with the user
 * by the method playTurn().
 * It will ask the user first to enter a two digits number, the first digit indicates
 * to the row index and the second digit indicates to the column index.
 * Then it will tries to fill the matrix board in these coordinates with the human player mark
 * if it didn't succeed (the chosen point is not BLANK or the chosen coordinates is illegal) it will
 * ask the user to enter another coordinates again.
 * @author Salah Mahmied.
 * @see Player
 */
public class HumanPlayer implements Player {
    private static final String INITIAL_MESSAGE_FORMAT = "Player %s, type coordinates: ";
    private static final String INVALID_MARK_POSITION_MESSAGE = "Invalid mark position. Please choose a " +
            "valid position: ";
    private static final String CHOOSING_OCCUPIED_POSITION_MESSAGE = "Mark position is already occupied. " +
            "Please choose a valid position: ";

    //This variable helps to check if this is the first time for the human player to choose
    //coordinates in his turn or it is not, and that to know if need to print the initial message or not.
    private boolean printInitialMessage;

    /**
     * The default constructor for the HumanPlayer class
     * it will initialize the printInitialMessage boolean variable with true value.
     */
    public HumanPlayer() {
        this.printInitialMessage = true;
    }

    /**
     * This method override from the Player interface it implements the interaction with the user
     * by asking him to enter a two digits number, the first digit indicates
     * to the row index and the second digit indicates to the column index.
     * Then it will tries to fill the matrix board in these coordinates with the human player mark
     * if it didn't succeed (the chosen point is not BLANK or the chosen coordinates is illegal) it will
     * ask the user to enter another coordinates again.
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (this.printInitialMessage) {
            System.out.print(String.format(INITIAL_MESSAGE_FORMAT, mark));
        }
        this.printInitialMessage = true;
        int userInputNumber = KeyboardInput.readInt();
        int userInputRow = userInputNumber / 10;
        int userInputColumn = userInputNumber % 10;

        if (userInputRow >= board.getSize() || userInputColumn >= board.getSize()) {
            System.out.print(INVALID_MARK_POSITION_MESSAGE);
            this.printInitialMessage = false;
            this.playTurn(board, mark);
        }
        if(!board.putMark(mark, userInputRow, userInputColumn)) {
            System.out.print(CHOOSING_OCCUPIED_POSITION_MESSAGE);
            this.printInitialMessage = false;
            this.playTurn(board, mark);
        }
    }
}
