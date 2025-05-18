package walking.game;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import walking.game.WalkingBoard;
import walking.game.util.Direction;

import java.util.Arrays;


public class WalkingBoardWithPlayersTest {
    @Test
    public void walk1() {
        WalkingBoardWithPlayers board = new WalkingBoardWithPlayers(4, 2);
        int[] expectedScore = board.walk(1,2,3,3,2,1,2,1);//1, 3, 6, 9, 11, 12, 14, 15
        assertEquals(12, expectedScore[0]);
        assertEquals(28, expectedScore[1]);
        int[][] expectedBoard = new int[][] {{4,  2, 3, 4},
                                             {4,  4, 4, 7},
                                             {13, 4, 4, 8},
                                             {12,11,10, 9}};
        
        
        for(int i = 0; i < expectedBoard.length; i++) {
            for(int j = 0; j < expectedBoard[i].length; j++) {
                assertEquals(expectedBoard[i][j], board.getTiles()[i][j]);
            }
        }

        //WalkingBoardWithPlayers wb = new WalkingBoardWithPlayers(3, 2);
        //assertEquals(4, wb.walk(1, 2, 4, 3, 2, 4, 3, 2, 5, 4, 3, 4));
    }
    
    @Test
    public void walk2() {
        WalkingBoardWithPlayers board = new WalkingBoardWithPlayers(4, 3);
        int[] expectedScore = board.walk(1,2,3,3,2,1,2,1,3,1,2,5,3,1);
        assertEquals(47, expectedScore[0]);
        assertEquals(41, expectedScore[1]);
        assertEquals(12, expectedScore[2]);
        int[][] expectedBoard = new int[][] {{13,  2,  3, 4 },
                                             {13,  4,  4, 10},
                                             {13,  4,  4, 11},
                                             {13, 13, 13, 12}};
        
        
        for(int i = 0; i < expectedBoard.length; i++) {
            for(int j = 0; j < expectedBoard[i].length; j++) {
                assertEquals(expectedBoard[i][j], board.getTiles()[i][j]);
            }
        }
    }
}