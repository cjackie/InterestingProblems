package com.chaojie.movetheblock.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int nRow;
    private int nColumns;
    private Block redBlock;
    private List<Block> plainBlocks;
    private EmptySpace emptySpace;
    private int[][] gameState;

    public Game(int nRow, int nColumns, List<Block> plainBlocks, Block redBlock) {
        this.nRow = nRow;
        this.nColumns = nColumns;
        this.plainBlocks = plainBlocks;
        this.redBlock = redBlock;

        gameState = new int[nRow][nColumns];
        emptySpace = new EmptySpace(nRow, nColumns);
        updateState();
    }

    public int getnRow() {
        return nRow;
    }

    public int getnColumns() {
        return nColumns;
    }

    public Block getRedBlock() {
        return redBlock;
    }

    public List<Block> getPlainBlocks() {
        return plainBlocks;
    }

    public EmptySpace getEmptySpace() {
        return emptySpace;
    }

    public void apply(Action action) {
        Block blockFound = findBlockById(action.getBlockId());

        if (blockFound == null)
            throw new IllegalArgumentException("Illegal action: no block found");

        blockFound.apply(action);
        updateState();
    }

    public void undo(Action action) {
        Block blockFound = findBlockById(action.getBlockId());
        if (blockFound == null)
            throw new IllegalArgumentException("Illegal action: no block found");

        blockFound.undo(action);
        updateState();
    }

    private Block findBlockById(int blockId) {
        Block blockFound = null;
        if (redBlock.getId() == blockId)
            blockFound = redBlock;

        for (Block ablock : plainBlocks) {
            if (ablock.getId() == blockId) {
                blockFound = ablock;
                break;
            }
        }

        return blockFound;
    }

    private void updateState() {
        gameState = new int[nRow][nColumns];
        List<Block> blocks = new ArrayList<>(plainBlocks);
        blocks.add(redBlock);
        for (Block block : blocks) {
            for (int i = 0; i < block.getLength(); i++) {
                int row = block.getOrientation() == Block.BlockOrientation.HORIZONTAL ? block.getRow() : block.getRow()+i;
                int col = block.getOrientation() == Block.BlockOrientation.HORIZONTAL ? block.getColumn()+i : block.getColumn();
                gameState[row][col] = block.getId();
            }
        }

        for (int row = 0; row < nRow; row++) {
            for (int col = 0; col < nColumns; col++) {
                if (gameState[row][col] == 0)
                    emptySpace.empty(row, col);
                else
                    emptySpace.occupy(row, col);
            }
        }
    }

    public String state() {
        StringBuilder state = new StringBuilder();
        for (int row = 0; row < nRow; row++) {
            for (int col = 0; col < nColumns; col++) {
                state.append(gameState[row][col]);
                state.append(",");
            }
            state.append("\n");
        }

        return state.toString();
    }

    public boolean isSolved() {
        return redBlock.getRow() + redBlock.getLength() == nRow;
    }

}
