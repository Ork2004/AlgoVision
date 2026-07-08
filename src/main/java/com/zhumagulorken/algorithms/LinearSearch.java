package com.zhumagulorken.algorithms;

import java.util.function.IntConsumer;

public class LinearSearch implements SearchAlgorithm {
    @Override
    public int search(int[] array, int target, IntConsumer onUpdate) throws InterruptedException {
        for (int i = 0; i < array.length; i++) {
            onUpdate.accept(i);
            Thread.sleep(20);
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
