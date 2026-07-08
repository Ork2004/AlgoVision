package com.zhumagulorken.algorithms;

public class RadixSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        if (array.length == 0) return;

        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp, onUpdate);
        }
    }

    private void countingSortByDigit(int[] array, int exp, Runnable onUpdate) throws InterruptedException {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : array) {
            count[(value / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            onUpdate.run();
            Thread.sleep(20);
        }
    }
}
