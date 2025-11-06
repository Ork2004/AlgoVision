package com.zhumagulorken.algorithms;

public class BubbleSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                onUpdate.run();
                Thread.sleep(20);
            }
        }
    }
}
