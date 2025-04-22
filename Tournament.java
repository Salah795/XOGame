/**
 * Class for defining the Tournament for the XO game with a board object from
 * class Board of a specific size (which is a squared matrix)
 * for two players and a specific number of rounds.
 * The content of each point in the board matrix will be BLANK, X, or O from the Mark enum.
 *
 * @author Salah Mahmied
 * @see Board
 * @see Mark
 */
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

    /**
     * The constructor for defining a Tournament, with receiving the number of rounds,
     * renderer,and the two players as parameters.
     * @param rounds The number of rounds in the Tournament.
     * @param renderer The game renderer.
     * @param player1 The first player of the game.
     * @param player2 The second player of the game.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.roundsNumber = rounds;
        this.renderer = renderer;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
    }

    /**
     * This method will start the tournament, it will play all the rounds and count
     * the number of rounds that the first player won, the number of rounds that the second
     * player won, and the number of ties.
     * First the first player will take the mark X and the second one will take the mark O
     * and after each round the marks will flip.
     * After the end of all the rounds it will print the tournament finish message with the results
     * of the tournament: number of rounds that the first player won, the number of rounds that the second
     * player won, and the number of ties.
     * @param size The game board size.
     * @param winStreak The win streak.
     * @param playerName1 The string that represents the first player name.
     * @param playerName2 The string that represents the second player name.
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        Player playerX, playerO;
        int firstPlayerWinsCounter = 0, secondPlayerWinsCounter = 0, tiesCounter = 0;
        for(int round = 0; round < roundsNumber; round++) {
            //Choosing the mark of each player in the current round.
            if(round % 2 == 0){
                playerX = this.firstPlayer;
                playerO = this.secondPlayer;
            } else {
                playerX = this.secondPlayer;
                playerO = this.firstPlayer;
            }
            Game game = new Game(playerX, playerO, size, winStreak, this.renderer);
            Mark winnerMark = game.run();
            //Updating the counters after the current round play.
            switch (winnerMark) {
                case X:
                    if(round % 2 == 0) {
                        firstPlayerWinsCounter++;
                    } else {
                        secondPlayerWinsCounter++;
                    }
                    break;
                case O:
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
        //Printing the tournament finish message with the results
        // of the tournament: number of rounds that the first player won,
        // the number of rounds that the second player won, and the number of ties.
        System.out.println(TOURNAMENT_END_MESSAGE);
        System.out.println(String.format(FIRST_PLAYER_WINS_MESSAGE_FORMAT, playerName1,
                firstPlayerWinsCounter));
        System.out.println(String.format(SECOND_PLAYER_WINS_MESSAGE_FORMAT, playerName2,
                secondPlayerWinsCounter));
        System.out.println(TIES_NUMBER_MESSAGE_FORMAT + tiesCounter);
    }

    /**
     * The main method that will activate the objects for the current tournament based
     * on the arguments that will be received from the command line and run the tournament.
     * @param args The arguments that will be received from the command line to start the tournament.
     */
    public static void main(String[] args) {
        int roundsNumber = Integer.parseInt(args[ROUNDS_INDEX]);
        int boardSize = Integer.parseInt(args[BOARD_SIZE_INDEX]);
        int tournamentWinStreak = Integer.parseInt(args[WIN_STREAK_INDEX]);
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();
        Renderer tournamentRenderer;
        tournamentRenderer = rendererFactory.buildRenderer(args[RENDER_TARGET_INDEX], boardSize);
        Player firstPlayer = playerFactory.buildPlayer(args[FIRST_PLAYER_NAME_INDEX]);
        Player secondPlayer = playerFactory.buildPlayer(args[SECOND_PLAYER_NAME_INDEX]);
        Tournament tournament = new Tournament(roundsNumber, tournamentRenderer, firstPlayer, secondPlayer);
        tournament.playTournament(boardSize, tournamentWinStreak, args[FIRST_PLAYER_NAME_INDEX],
                args[SECOND_PLAYER_NAME_INDEX]);
    }
}
