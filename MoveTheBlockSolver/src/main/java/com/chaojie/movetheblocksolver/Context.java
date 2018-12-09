package com.chaojie.movetheblocksolver;

import java.util.List;

public class Context {
    private int nRow;
    private int nColumns;

    private Block redBlock;
    private List<Block> plainBlocks;

    private EmptySpace emptySpace;
    private int exit;

    private Context() {}

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

    public int getExit() {
        return exit;
    }

    public static class ContextBuilder {
        private Context context = new Context();

        public ContextBuilder setNRow(int nRow) {
            context.nRow = nRow;
            return this;
        }

        public ContextBuilder setnRow(int nRow) {
            context.nRow = nRow;
            return this;
        }

        public ContextBuilder setnColumns(int nColumns) {
            context.nColumns = nColumns;
            return this;
        }

        public ContextBuilder setRedBlock(Block redBlock) {
            context.redBlock = redBlock;
            return this;
        }

        public ContextBuilder setPlainBlocks(List<Block> plainBlocks) {
            context.plainBlocks = plainBlocks;
            return this;
        }

        public ContextBuilder setEmptySpace(EmptySpace emptySpace) {
            context.emptySpace = emptySpace;
            return this;
        }

        public ContextBuilder setExit(int exit) {
            context.exit = exit;
            return this;
        }

        public Context build() {
            return context;
        }
    }
}
