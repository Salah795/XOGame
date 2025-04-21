public class HumanPlayer implements Player {
    private static final String INITIAL_MESSAGE_FORMAT = "Player %s, type coordinates: ";
    private static final String INVALID_MARK_POSITION_MESSAGE = "Invalid mark position. Please choose a " +
            "valid position: ";
    private static final String CHOOSING_OCCUPIED_POSITION_MESSAGE = "Mark position is already occupied. " +
            "Please choose a valid position: ";

    private boolean printInitialMessage;

    public HumanPlayer() {
        this.printInitialMessage = true;
    }

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
