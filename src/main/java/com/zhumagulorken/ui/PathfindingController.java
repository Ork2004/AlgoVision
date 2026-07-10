package com.zhumagulorken.ui;

import com.zhumagulorken.algorithms.BFSPathfinder;
import com.zhumagulorken.algorithms.DijkstraPathfinder;
import com.zhumagulorken.algorithms.PathfindingAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class PathfindingController {
    private static final int ROWS = 20;
    private static final int COLS = 32;
    private static final double CELL_SIZE = 25;

    private enum CellState { EMPTY, WALL, WEIGHT, START, END }

    private static final int WEIGHT_COST = 5;

    @FXML private Canvas gridCanvas;
    @FXML private Button clearButton, startButton;
    @FXML private ChoiceBox<String> cellModeChoice;
    @FXML private ChoiceBox<String> pathAlgorithmChoice;

    private final CellState[][] grid = new CellState[ROWS][COLS];
    private boolean[][] visited = new boolean[ROWS][COLS];
    private boolean[][] path = new boolean[ROWS][COLS];
    private int[] start;
    private int[] end;

    @FXML
    public void initialize() {
        cellModeChoice.getItems().addAll("Wall", "Weight", "Start", "End");
        cellModeChoice.setValue("Wall");
        pathAlgorithmChoice.getItems().addAll("BFS", "Dijkstra");
        pathAlgorithmChoice.setValue("BFS");

        clearButton.setOnAction(e -> resetGrid());
        startButton.setOnAction(e -> startPathfinding());
        gridCanvas.setOnMouseClicked(this::handleCanvasClick);

        resetGrid();
    }

    private void resetGrid() {
        for (CellState[] row : grid) {
            Arrays.fill(row, CellState.EMPTY);
        }
        start = new int[]{0, 0};
        end = new int[]{ROWS - 1, COLS - 1};
        grid[start[0]][start[1]] = CellState.START;
        grid[end[0]][end[1]] = CellState.END;
        visited = new boolean[ROWS][COLS];
        path = new boolean[ROWS][COLS];
        draw();
    }

    private void handleCanvasClick(MouseEvent event) {
        int col = (int) (event.getX() / CELL_SIZE);
        int row = (int) (event.getY() / CELL_SIZE);
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;

        switch (cellModeChoice.getValue()) {
            case "Start" -> {
                grid[start[0]][start[1]] = CellState.EMPTY;
                start = new int[]{row, col};
                grid[row][col] = CellState.START;
            }
            case "End" -> {
                grid[end[0]][end[1]] = CellState.EMPTY;
                end = new int[]{row, col};
                grid[row][col] = CellState.END;
            }
            case "Weight" -> paintCell(row, col, CellState.WEIGHT);
            default -> paintCell(row, col, CellState.WALL);
        }
        draw();
    }

    private void paintCell(int row, int col, CellState target) {
        if (grid[row][col] == target) {
            grid[row][col] = CellState.EMPTY;
        } else if (grid[row][col] != CellState.START && grid[row][col] != CellState.END) {
            grid[row][col] = target;
        }
    }

    private PathfindingAlgorithm getSelectedAlgorithm() {
        return switch (pathAlgorithmChoice.getValue()) {
            case "BFS" -> new BFSPathfinder();
            case "Dijkstra" -> new DijkstraPathfinder();
            default -> null;
        };
    }

    private void startPathfinding() {
        PathfindingAlgorithm algorithm = getSelectedAlgorithm();
        if (algorithm == null) return;

        int[][] cost = new int[ROWS][COLS];
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                cost[r][c] = switch (grid[r][c]) {
                    case WALL -> 0;
                    case WEIGHT -> WEIGHT_COST;
                    default -> 1;
                };

        visited = new boolean[ROWS][COLS];
        path = new boolean[ROWS][COLS];

        int[] searchStart = start;
        int[] searchEnd = end;

        new Thread(() -> {
            try {
                List<int[]> foundPath = algorithm.findPath(cost, searchStart, searchEnd, (row, col) -> {
                    visited[row][col] = true;
                    javafx.application.Platform.runLater(this::draw);
                });
                for (int[] cell : foundPath) {
                    path[cell[0]][cell[1]] = true;
                }
                javafx.application.Platform.runLater(this::draw);
            } catch (InterruptedException ignored) {}
        }).start();
    }

    private void draw() {
        GraphicsContext gc = gridCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gridCanvas.getWidth(), gridCanvas.getHeight());

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                gc.setFill(cellColor(r, c));
                gc.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
            }
        }
    }

    private Color cellColor(int row, int col) {
        CellState state = grid[row][col];
        if (state == CellState.START) return Color.LIMEGREEN;
        if (state == CellState.END) return Color.CRIMSON;
        if (state == CellState.WALL) return Color.rgb(40, 40, 40);
        if (path[row][col]) return Color.GOLD;
        if (visited[row][col]) return Color.LIGHTBLUE;
        if (state == CellState.WEIGHT) return Color.SANDYBROWN;
        return Color.WHITESMOKE;
    }
}
