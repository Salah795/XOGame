public enum Mark{
    BLANK, X, O;

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