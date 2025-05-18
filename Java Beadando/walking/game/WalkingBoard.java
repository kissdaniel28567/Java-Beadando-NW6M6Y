package walking.game;

import walking.game.util.Direction;
import java.util.Arrays;

public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    public static final int BASE_TILE_SCORE = 3;

    public int[][] getTiles() {
        return Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
    }
    
    public WalkingBoard(int size) {
        this.tiles = new int[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(size > BASE_TILE_SCORE) {
                    tiles[i][j] = size;
                } else {
                    tiles[i][j] = BASE_TILE_SCORE;
                }
            }
        }
        this.x = 0;
        this.y = 0;
    }

    public WalkingBoard(int[][] tiles) {
        int maxLength = Arrays.stream(tiles).mapToInt(x -> x.length).max().getAsInt();
        this.tiles = new int[tiles.length][maxLength];
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < maxLength; j++) {
                if(tiles[i].length > j && tiles[i][j] > BASE_TILE_SCORE) {
                    this.tiles[i][j] = tiles[i][j];
                } else {
                    this.tiles[i][j] = BASE_TILE_SCORE;
                }
            }
        }
        this.x = 0;
        this.y = 0;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
    }

    public int getTile(int x, int y) {
        if (isValidPosition(x, y)) {
            return tiles[x][y];
        }
        throw new IllegalArgumentException(); // valszeg error kell majd
    }

    public static int getXStep(Direction direction) {
        switch (direction) {
            case UP:
                return -1;
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }

    public static int getYStep(Direction direction) {
        switch (direction) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }

    public int moveAndSet(Direction direction, int value) {
        int newX = x + getXStep(direction);
        int newY = y + getYStep(direction);
        if (isValidPosition(newX, newY)) {
            x = newX;
            y = newY;
            int oldValue = tiles[x][y];
            tiles[x][y] = value;
            return oldValue;
        }
        return 0;
    }
}