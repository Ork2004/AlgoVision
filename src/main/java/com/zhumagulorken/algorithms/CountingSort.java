package com.zhumagulorken.algorithms;

public class CountingSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        if (array.length == 0) return;

        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }

        int[] count = new int[max + 1];
        for (int value : array) {
            count[value]++;
        }

        int index = 0;
        for (int value = 0; value <= max; value++) {
            while (count[value] > 0) {
                array[index++] = value;
                count[value]--;
                onUpdate.run();
                Thread.sleep(20);
            }
        }
    }
}
