package com.zhumagulorken.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BucketSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        if (array.length == 0) return;

        int max = array[0];
        int min = array[0];
        for (int value : array) {
            if (value > max) max = value;
            if (value < min) min = value;
        }

        int bucketCount = array.length;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        int range = max - min + 1;
        for (int value : array) {
            int bucketIndex = (int) ((long) (value - min) * bucketCount / range);
            buckets.get(bucketIndex).add(value);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            insertionSort(bucket);
            for (int value : bucket) {
                array[index++] = value;
                onUpdate.run();
                Thread.sleep(20);
            }
        }
    }

    private void insertionSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
