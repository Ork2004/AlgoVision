package com.zhumagulorken.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPathfinder implements PathfindingAlgorithm {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @Override
    public List<int[]> findPath(boolean[][] walls, int[] start, int[] end, CellVisitor onVisit) throws InterruptedException {
        int rows = walls.length;
        int cols = walls[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[][] parentRow = new int[rows][cols];
        int[][] parentCol = new int[rows][cols];
        for (int[] row : parentRow) Arrays.fill(row, -1);
        for (int[] row : parentCol) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            onVisit.visit(current[0], current[1]);
            Thread.sleep(20);

            if (current[0] == end[0] && current[1] == end[1]) {
                return reconstructPath(parentRow, parentCol, start, end);
            }

            for (int[] dir : DIRECTIONS) {
                int nr = current[0] + dir[0];
                int nc = current[1] + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && !walls[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    parentRow[nr][nc] = current[0];
                    parentCol[nr][nc] = current[1];
                    queue.add(new int[]{nr, nc});
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
