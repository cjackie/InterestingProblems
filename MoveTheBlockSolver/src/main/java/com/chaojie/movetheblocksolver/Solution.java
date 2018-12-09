package com.chaojie.movetheblocksolver;

public class Solution {
    public static void main(String[] args) {
        GameLoader gameLoader = new GameLoader();
        Context context = gameLoader.load();
        MoveTheBlockSolverV1 moveTheBlockSolverV1 = new MoveTheBlockSolverV1();
        moveTheBlockSolverV1.setContext(context);

        moveTheBlockSolverV1.solve();
    }
}
