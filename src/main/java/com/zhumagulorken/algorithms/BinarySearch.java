package com.zhumagulorken.algorithms;

import java.util.function.IntConsumer;

public class BinarySearch implements SearchAlgorithm {
    @Override
    public int search(int[] array, int target, IntConsumer onUpdate) throws InterruptedException {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            onUpdate.accept(mid);
            Thread.sleep(20);

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
