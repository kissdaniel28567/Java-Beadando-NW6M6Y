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

public class WalkingBoardTest {

    @ParameterizedTest
    @CsvSource({"1", "2", "30"})
    public void testSimpleInit(int size) {
        WalkingBoard board = new WalkingBoard(size);
        assertNotNull(board);
        assertEquals(size, board.getTiles().length);
        assertEquals(size, board.getTiles()[0].length);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tileValue = board.getTiles()[i][j];
                if(size > 3) {
                    assertEquals(tileValue, size);
                } else {
                    assertEquals(tileValue, 3);
                }
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"'1,2,3 1,2,2 1,2'", "'1,2,3,4 1,3,2 1,1'", "'1,2,6 5,6,3 8,0, 1'"})
    public void testCustomInit(String expected) {
        String[] rows = expected.split(" ");
        int[][] tiles = new int[rows.length][];

        for(int i = 0; i < rows.length; i++) {
            tiles[i] = Arrays.stream(rows[i].split(","))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        WalkingBoard board = new WalkingBoard(tiles);
        assertNotNull(board);
        assertEquals(rows.length, board.getTiles().length);
        for(int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[0].length; j++) {
                int tileValue = board.getTiles()[i][j];
                if(tiles[i].length > j && tiles[i][j] > 3) {
                    assertEquals(tiles[i][j], tileValue);
                } else {
                    assertEquals(3, tileValue);
                }
                
            }
        }

        tiles[1][2] = 1;
        tiles[2][1] = 1;

        assertEquals(3, board.getTiles()[1][2]);
        assertEquals(3, board.getTiles()[2][1]);

        board.getTiles()[1][2] = 1;
        board.getTiles()[2][1] = 1;

        assertEquals(3, board.getTiles()[1][2]);
        assertEquals(3, board.getTiles()[2][1]);
    }

    @ParameterizedTest
    @CsvSource({"1,1,5", "0,0,3", "2,0,3", "0,3,4"})
    public void testCustomInit(int x, int y, int expected) {
        int[][] tiles = new int[3][];
        tiles[0] = new int[]{1,2,3,4,5};
        tiles[1] = new int[]{4,5};
        tiles[2] = new int[]{1};


        WalkingBoard board = new WalkingBoard(tiles);
        assertNotNull(board);
        assertEquals(tiles.length, board.getTiles().length);

        assertEquals(expected, board.getTiles()[x][y]);

        tiles[x][y] = 1;

        assertEquals(expected, board.getTiles()[x][y]);

        board.getTiles()[x][y] = 1;
        assertEquals(expected, board.getTiles()[x][y]);
    }



    
    @Test
    public void testIsValidPosition() {
        WalkingBoard board = new WalkingBoard(3);
        assertTrue(board.isValidPosition(0, 0));
        assertTrue(board.isValidPosition(2, 2));
        assertFalse(board.isValidPosition(-1, 0));
        assertFalse(board.isValidPosition(0, 3));
        assertFalse(board.isValidPosition(3, 2));
    }

    @Test
    public void testMoves() {
        WalkingBoard board = new WalkingBoard(3);
        assertEquals(3, board.moveAndSet(Direction.RIGHT, 10));//valid
        int[] position = board.getPosition();
        assertEquals(0, position[0]);
        assertEquals(1, position[1]);
        assertEquals(10, board.getTile(0, 1));

        assertEquals(0, board.moveAndSet(Direction.UP, 10));//invalid
        position = board.getPosition();
        assertEquals(0, position[0]);
        assertEquals(1, position[1]);

        assertEquals(3, board.moveAndSet(Direction.LEFT, 10));//valid
        position = board.getPosition();
        assertEquals(0, position[0]);
        assertEquals(0, position[1]);
        assertEquals(10, board.getTile(0, 0));

        assertEquals(0, board.moveAndSet(Direction.LEFT, 10));//invalid
        position = board.getPosition();
        assertEquals(0, position[0]);
        assertEquals(0, position[1]);

        assertEquals(3, board.moveAndSet(Direction.DOWN, 10));//valid
        position = board.getPosition();
        assertEquals(1, position[0]);
        assertEquals(0, position[1]);
        assertEquals(10, board.getTile(1, 0));

        assertEquals(3, board.moveAndSet(Direction.DOWN, 10));//valid
        position = board.getPosition();
        assertEquals(2, position[0]);
        assertEquals(0, position[1]);
        assertEquals(10, board.getTile(2, 0));

        assertEquals(0, board.moveAndSet(Direction.DOWN, 15));//invalid
        position = board.getPosition();
        assertEquals(2, position[0]);
        assertEquals(0, position[1]);
    }
}
