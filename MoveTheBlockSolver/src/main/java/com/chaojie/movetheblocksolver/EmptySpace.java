package com.chaojie.movetheblocksolver;

public class EmptySpace {
    public static final int OCCUPIED = 0;
    public static final int EMPTY = 1;

    private int[][] emptySpace;

    public boolean isEmptyAt(int row, int col) {
        return emptySpace[row][col] == EMPTY;
    }

    public void empty(int row, int col) {
        emptySpace[row][col] = EMPTY;
    }

    public void occupy(int row, int col) {
        emptySpace[row][col] = OCCUPIED;
    }

    public int[][] getEmptySpace() {
        return emptySpace;
    }

    public void setEmptySpace(int[][] emptySpace) {
        this.emptySpace = emptySpace;
    }


}
