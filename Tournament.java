public class Tournament {
    private static final String TOURNAMENT_END_MESSAGE = "######### Results #########";
    private static final String FIRST_PLAYER_WINS_MESSAGE_FORMAT = "Player 1, %s won: %s rounds";
    private static final String SECOND_PLAYER_WINS_MESSAGE_FORMAT = "Player 2, %s won: %s rounds";
    private static final String TIES_NUMBER_MESSAGE_FORMAT = "Ties: ";

    private int roundsNumber;
    private Renderer renderer;
    private Player firstPlayer;
    private Player secondPlayer;

    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.roundsNumber = rounds;
        this.renderer = renderer;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
    }

    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        Player playerX, playerO;
        int firstPlayerWinsCounter = 0, secondPlayerWinsCounter = 0, tiesCounter = 0;
        for(int round = 0; round < roundsNumber; round++) {
            if(round % 2 == 0){
                playerX = this.firstPlayer;
                playerO = this.secondPlayer;
            } else {
                playerX = this.secondPlayer;
                playerO = this.firstPlayer;
            }
            Game game = new Game(playerX, playerO, size, winStreak, this.renderer);
            Mark winnerMark = game.run();
            switch (winnerMark) {
                case Mark.X:
                    if(round % 2 == 0) {
                        firstPlayerWinsCounter++;
                    } else {
                        secondPlayerWinsCounter++;
                    }
                    break;
                case Mark.O:
                    if(round % 2 == 0) {
                        secondPlayerWinsCounter++;
                    } else {
                        firstPlayerWinsCounter++;
                    }
                    break;
                default:
                    tiesCounter++;
            }
        }
        System.out.println(TOURNAMENT_END_MESSAGE);
        System.out.println(String.format(FIRST_PLAYER_WINS_MESSAGE_FORMAT, playerName1,
                firstPlayerWinsCounter));
        System.out.println(String.format(SECOND_PLAYER_WINS_MESSAGE_FORMAT, playerName2,
                secondPlayerWinsCounter));
        System.out.println(TIES_NUMBER_MESSAGE_FORMAT + tiesCounter);
    }

    public static void main(String[] args) {

    }
}
