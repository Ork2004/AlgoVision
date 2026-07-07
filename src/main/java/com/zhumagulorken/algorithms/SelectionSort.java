package com.zhumagulorken.algorithms;

public class SelectionSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                onUpdate.run();
                Thread.sleep(20);
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
            onUpdate.run();
        }
    }
}
