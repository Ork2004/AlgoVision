package com.zhumagulorken.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPathfinder implements PathfindingAlgorithm {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @Override
    public List<int[]> findPath(int[][] cost, int[] start, int[] end, CellVisitor onVisit) throws InterruptedException {
        int rows = cost.length;
        int cols = cost[0].length;

        int[][] distance = new int[rows][cols];
        for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        int[][] parentRow = new int[rows][cols];
        int[][] parentCol = new int[rows][cols];
        for (int[] row : parentRow) Arrays.fill(row, -1);
        for (int[] row : parentCol) Arrays.fill(row, -1);
        boolean[][] settled = new boolean[rows][cols];

        distance[start[0]][start[1]] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        queue.add(new int[]{start[0], start[1], 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];
            if (settled[r][c]) continue;
            settled[r][c] = true;
            onVisit.visit(r, c);
            Thread.sleep(20);

            if (r == end[0] && c == end[1]) {
                return reconstructPath(parentRow, parentCol, start, end);
            }

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols
                        || cost[nr][nc] <= 0 || settled[nr][nc]) {
                    continue;
                }

                int newDistance = distance[r][c] + cost[nr][nc];
                if (newDistance < distance[nr][nc]) {
                    distance[nr][nc] = newDistance;
                    parentRow[nr][nc] = r;
                    parentCol[nr][nc] = c;
                    queue.add(new int[]{nr, nc, newDistance});
                }
            }
        }

        return List.of();
    }

    private List<int[]> reconstructPath(int[][] parentRow, int[][] parentCol, int[] start, int[] end) {
        LinkedList<int[]> path = new LinkedList<>();
        int r = end[0], c = end[1];
        while (!(r == start[0] && c == start[1])) {
            path.addFirst(new int[]{r, c});
            int pr = parentRow[r][c];
            int pc = parentCol[r][c];
            r = pr;
            c = pc;
        }
        path.addFirst(start);
        return path;
    }
}
