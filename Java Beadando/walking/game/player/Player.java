package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    protected Direction direction = Direction.UP;

    public int getScore() {
        return score;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void turn() {
        direction = Direction.values()[(direction.ordinal()+1) % 4];
        //direction = Direction.RIGHT;
    }
}