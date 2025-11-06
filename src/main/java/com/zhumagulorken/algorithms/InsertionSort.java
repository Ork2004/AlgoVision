package com.zhumagulorken.algorithms;

public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                onUpdate.run();
                Thread.sleep(20);
            }
            array[j + 1] = key;
            onUpdate.run();
        }
    }
}
