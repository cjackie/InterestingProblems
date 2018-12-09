package com.chaojie.movetheblocksolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Block {
    public static final char DIRECTION_SOUTH = 'S';
    public static final char DIRECTION_EAST = 'E';

    private int id;
    private int row;
    private int column;
    private int length;

    private char direction;

    private Context context;

    public int[] possibleMoves() {
        List<Integer> moves = new ArrayList<>();
        if (direction == DIRECTION_SOUTH) {
            for (int newRow = 0; newRow <= context.getnRow() - length; newRow++) {
                if (newRow == row)
                    continue;

                if (!isValidMove(newRow))
                    continue;

                moves.add(newRow);
            }
        } else if (direction == DIRECTION_EAST) {
            for (int newCol = 0; newCol < context.getnColumns() - length; newCol++) {
                if (newCol == column)
                    continue;

                if (!isValidMove(newCol))
                    continue;

                moves.add(newCol);
            }
        } else
            throw new IllegalArgumentException("Unknown direction: " + direction);

        int[] movesArray = new int[moves.size()];
        for (int i = 0; i < moves.size(); i++)
            movesArray[i] = moves.get(i);

        return movesArray;
    }

    public boolean isValidMove(int dest) {
        boolean hasCollision = false;
        if (direction == DIRECTION_SOUTH) {
            for (int nextRow = row; nextRow < row + length; nextRow++) {
                if (!context.getEmptySpace().isEmptyAt(nextRow, column)) {
                    hasCollision = true;
                    break;
                }
            }
        } else if (direction == DIRECTION_EAST) {
            for (int nextCol = row; nextCol < column + length; nextCol++) {
                if (!context.getEmptySpace().isEmptyAt(row, nextCol)) {
                    hasCollision = true;
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown direction: " + direction);
        }

        return hasCollision;
    }

    public void move(int dest) {
        if (isValidMove(dest))
            throw new IllegalArgumentException("Invalid move of block " + this + " to " + dest);

        if (direction == DIRECTION_SOUTH)
            row = dest;
        else if (direction == DIRECTION_EAST)
            column = dest;
        else
            throw new IllegalArgumentException("Unknown direction: " + direction);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return id == block.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
