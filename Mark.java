/**
 * Enum that defines the possible mark in the game board.
 * The marks can be X, O, or BLANK.
 * @author Salah Mahmied.
 */
public enum Mark{
    BLANK, X, O;

    /**
     * This method will return the mark of the object of type enum mark as a string.
     * @return The string of the mark of an object from type enum mark.
     */
    public String toString() {
        switch (this) {
            case BLANK:
                return "BLANK";
            case X:
                return "X";
            case O:
                return "O";
            default:
                return "";
        }
    }
}