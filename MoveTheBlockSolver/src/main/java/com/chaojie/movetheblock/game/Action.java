package com.chaojie.movetheblock.game;

import java.util.concurrent.atomic.AtomicLong;

public class Action {
    private static final AtomicLong ID = new AtomicLong(0);

    private long actionId = ID.incrementAndGet();
    private int blockId;
    private int moves;

    public Action(int blockId, int moves) {
        this.blockId = blockId;
        this.moves = moves;
    }

    public String toString() {
        return "Block id=" + blockId + " moves " + moves + " steps";
    }

    public Action clone() {
        return new Action(blockId, moves);
    }

    long getActionId() {
        return actionId;
    }

    int getBlockId() {
        return blockId;
    }

    int getMoves() {
        return moves;
    }
}
