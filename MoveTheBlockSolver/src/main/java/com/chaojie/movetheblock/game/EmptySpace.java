package com.chaojie.movetheblock.game;

public class EmptySpace {
    private static final int OCCUPIED = 0;
    private static final int EMPTY = 1;

    private int[][] emptySpace;

    EmptySpace(int nRow, int nCol) {
        emptySpace = new int[nRow][nCol];
    }

    boolean isEmptyAt(int row, int col) {
        return emptySpace[row][col] == EMPTY;
    }

    void empty(int row, int col) {
        emptySpace[row][col] = EMPTY;
    }

    void occupy(int row, int col) {
        emptySpace[row][col] = OCCUPIED;
    }
}
