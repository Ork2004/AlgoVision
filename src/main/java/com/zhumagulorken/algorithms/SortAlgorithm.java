package com.zhumagulorken.algorithms;

public interface SortAlgorithm {
    void sort(int[] array, Runnable onUpdate) throws InterruptedException;
}
