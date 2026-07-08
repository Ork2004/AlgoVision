package com.zhumagulorken.algorithms;

public class ShellSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                    onUpdate.run();
                    Thread.sleep(20);
                }
                array[j] = temp;
                onUpdate.run();
            }
        }
    }
}
