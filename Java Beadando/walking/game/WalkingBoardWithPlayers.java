package walking.game;

import walking.game.player.Player;
import walking.game.player.MadlyRotatingBuccaneer;
import java.util.Arrays;

public class WalkingBoardWithPlayers extends WalkingBoard{
    private Player[] players;
    private int round;
    public final static int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
        initPlayers(playerCount);
    }

    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initPlayers(playerCount);
    }

    private void initPlayers(int playerCount) {
        if(playerCount < 2) {
            throw new IllegalArgumentException();
        }

        /* A MadlyRotatingBuccaneer annyit fordul egyszerre, ahányszor sorra került.
            Első alkalommal tehát egyáltalán nem fordul.
            Következőnek annyit fordul, mint egy átlagos Player.
            Aztán hátrafordul és így tovább. */
        //MadlyRotatingBuccaneer
        players = Arrays.stream(new Player[playerCount])
                        .map(x -> new Player())
                        .toArray(Player[]::new);
        players[0] = new MadlyRotatingBuccaneer();
    }
    public int[] walk(int... stepCounts) {
        //[Ez itt azt akarja mondani ^ hogy a stepcount az az, hogy mennyit léptek a játékosok. PL [1,2,3,4,5] 1 lépett 1-et, második 2-t stb]
        int sumSteps = 0;
        
        for(int i = 0; i < stepCounts.length; i++) {
            
            Player actualPlayer = players[i % players.length];
            
            actualPlayer.turn();
            for(int j = 0; j < stepCounts[i]; j++) {
                actualPlayer.addToScore(moveAndSet(actualPlayer.getDirection(), ++sumSteps < SCORE_EACH_STEP ? sumSteps : SCORE_EACH_STEP));
            }
        }

        
        for(int i = 0; i < getTiles().length; i++) {
            for(int j = 0; j < getTiles()[i].length; j++) {
                System.out.print(getTiles()[i][j] + ", ");
            }
            System.out.println();
        }
        System.out.println();
        
        return Arrays.stream(players)
                     .mapToInt(Player::getScore)
                     .toArray();
    }


    //Getters setters
    public Player[] getPlayers() {
        return players.clone();
    }

    public void setPlayers(Player[] players) {
        this.players = players.clone();
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getSCORE_EACH_STEP() {
        return SCORE_EACH_STEP;
    }
}