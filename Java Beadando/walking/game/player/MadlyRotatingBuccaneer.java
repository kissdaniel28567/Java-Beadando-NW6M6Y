package walking.game.player;

import walking.game.util.Direction;

public class MadlyRotatingBuccaneer extends Player {
    private int turnCount;

    public void turn() {
        direction = Direction.values()[(direction.ordinal() + (turnCount)) % 4];
        turnCount++;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    /* sajat
    public void turn(int step) {
        direction = Direction.values()[(direction.ordinal() + (step - 1)) % 4];
    }*/
}