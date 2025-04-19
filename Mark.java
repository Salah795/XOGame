public enum Mark{
    BLANK, X, O;

    //TODO check if toString() method implementation is right.
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