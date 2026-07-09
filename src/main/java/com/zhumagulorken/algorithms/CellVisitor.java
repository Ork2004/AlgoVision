package com.zhumagulorken.algorithms;

@FunctionalInterface
public interface CellVisitor {
    void visit(int row, int col);
}
