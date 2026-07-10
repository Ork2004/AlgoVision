package com.zhumagulorken.algorithms;

import java.util.List;

public interface PathfindingAlgorithm {
    // cost[row][col] <= 0 is an impassable wall; positive values are the price of entering that cell.
    List<int[]> findPath(int[][] cost, int[] start, int[] end, CellVisitor onVisit) throws InterruptedException;
}
