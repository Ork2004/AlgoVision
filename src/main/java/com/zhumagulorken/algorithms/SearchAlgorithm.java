package com.zhumagulorken.algorithms;

import java.util.function.IntConsumer;

public interface SearchAlgorithm {
    int search(int[] array, int target, IntConsumer onUpdate) throws InterruptedException;
}
