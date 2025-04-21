public class Tournament {
    private static final int ROUNDS_INDEX = 0;
    private static final int BOARD_SIZE_INDEX = 1;
    private static final int WIN_STREAK_INDEX = 2;
    private static final int RENDER_TARGET_INDEX = 3;
    private static final int FIRST_PLAYER_NAME_INDEX = 4;
    private static final int SECOND_PLAYER_NAME_INDEX = 5;
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
        //TODO transfer the switch-case blocks in this method into factories.
        //TODO check if to put the strings in switch-case blocks into constants.
        int roundsNumber = Integer.parseInt(args[ROUNDS_INDEX]);
        int boardSize = Integer.parseInt(args[BOARD_SIZE_INDEX]);
        int tournamentWinStreak = Integer.parseInt(args[WIN_STREAK_INDEX]);
        PlayerFactory playerFactory = new PlayerFactory();
        Renderer tournamentRenderer;
        switch (args[RENDER_TARGET_INDEX]) {
            case "void":
                tournamentRenderer = new VoidRenderer();
                break;
            case "console":
                tournamentRenderer = new ConsoleRenderer(boardSize);
                break;
            default:
                tournamentRenderer = null;
        }
        Player firstPlayer = playerFactory.buildPlayer(args[FIRST_PLAYER_NAME_INDEX]);
        Player secondPlayer = playerFactory.buildPlayer(args[SECOND_PLAYER_NAME_INDEX]);
        Tournament tournament = new Tournament(roundsNumber, tournamentRenderer, firstPlayer, secondPlayer);
        tournament.playTournament(boardSize, tournamentWinStreak, args[FIRST_PLAYER_NAME_INDEX],
                args[SECOND_PLAYER_NAME_INDEX]);
    }
}
