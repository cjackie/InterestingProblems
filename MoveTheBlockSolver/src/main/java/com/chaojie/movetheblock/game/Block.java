package com.chaojie.movetheblock.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Block {
    private int id;
    private int row;
    private int column;
    private int length;

    private BlockOrientation orientation;
    private Game game;

    public Action[] getActions() {
        List<Action> actions = new ArrayList<>();

        int end = (orientation == BlockOrientation.HORIZONTAL) ? game.getnColumns() : game.getnRow();
        for (int i = 0; i < end; i++) {
            int curPosition = (orientation == BlockOrientation.HORIZONTAL) ? column : row;
            if (curPosition == i)
                continue;

            Action action = new Action(id, curPosition - i);
            if (isValidAction(action))
                actions.add(action);
        }

        Action[] actionsArray = new Action[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            actionsArray[i] = actions.get(i);
        }
        return actionsArray;
    }

    private boolean isValidAction(Action action) {
        if (action.getBlockId() != id)
            return false;

        if (action.getMoves() == 0)
            return false;

        for (int i = 1; i <= Math.abs(action.getMoves()); i++) {
            int nextRow;
            int nextColumn;
            if (action.getMoves() < 0) {
                nextRow = (orientation == BlockOrientation.HORIZONTAL) ? row : row - i;
                nextColumn = (orientation == BlockOrientation.HORIZONTAL) ? column - i : column;
            } else {
                nextRow = (orientation == BlockOrientation.HORIZONTAL) ? row : row + length + i - 1;
                nextColumn = (orientation == BlockOrientation.HORIZONTAL) ? column + length + i - 1 : column;
            }

            if (!game.getEmptySpace().isEmptyAt(nextRow, nextColumn))
                return false;
        }

        return true;
    }

    public void apply(Action action) {
        if (!isValidAction(action))
            throw new IllegalArgumentException("Invalid action: " + action.toString());

        row = orientation == BlockOrientation.HORIZONTAL ? row : row + action.getMoves();
        column = orientation == BlockOrientation.HORIZONTAL ? column + action.getMoves() : column;
    }

    public void undo(Action action) {
        apply(new Action(action.getBlockId(), -1 * action.getMoves()));
    }

    int getRow() {
        return row;
    }

    void setRow(int row) {
        this.row = row;
    }

    int getColumn() {
        return column;
    }

    void setColumn(int column) {
        this.column = column;
    }

    int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }

    void setGame(Game game) {
        this.game = game;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    BlockOrientation getOrientation() {
        return orientation;
    }

    void setOrientation(BlockOrientation orientation) {
        this.orientation = orientation;
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

    enum BlockOrientation {
        HORIZONTAL,
        VERTICAL
    }
}
