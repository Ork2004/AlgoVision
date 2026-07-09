package com.zhumagulorken.algorithms;

import java.util.List;

public interface PathfindingAlgorithm {
    List<int[]> findPath(boolean[][] walls, int[] start, int[] end, CellVisitor onVisit) throws InterruptedException;
}
