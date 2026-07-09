# AlgoVision

AlgoVision is a desktop application built with JavaFX that visualizes sorting, searching, and pathfinding algorithms in real time. Each algorithm runs on a background thread and renders its progress on a canvas — bars for sorting/searching, a clickable grid for pathfinding — so you can watch it work step by step.

## Features

- Real-time visualization of sorting, searching, and pathfinding algorithms on a `Canvas`
- Randomized array generation with a single click, and a clickable grid for building mazes
- Algorithm selection via dropdown menus, tabbed by category (Sorting & Searching, Pathfinding)
- Non-blocking UI — every algorithm runs on a background thread while the JavaFX render loop stays responsive
- Clean separation between algorithm logic and UI: sorting, searching, and pathfinding each implement their own interface (`SortAlgorithm`, `SearchAlgorithm`, `PathfindingAlgorithm`) and report progress through a callback, so the visualizer has no knowledge of how a given algorithm works internally

## Algorithms implemented

### Sorting

| Algorithm      | Time complexity (avg) | Space complexity |
|----------------|------------------------|-------------------|
| Bubble Sort    | O(n^2)                 | O(1)              |
| Insertion Sort | O(n^2)                 | O(1)              |
| Selection Sort | O(n^2)                 | O(1)              |
| Heap Sort      | O(n log n)             | O(1)              |
| Merge Sort     | O(n log n)             | O(n)              |
| Quick Sort     | O(n log n)             | O(log n)          |
| Counting Sort  | O(n + k)               | O(n + k)          |
| Bucket Sort    | O(n + k)               | O(n + k)          |
| Radix Sort     | O(d * (n + k))         | O(n + k)          |
| Shell Sort     | O(n log^2 n)           | O(1)              |

### Searching

| Algorithm      | Time complexity (avg) | Space complexity |
|----------------|------------------------|-------------------|
| Linear Search  | O(n)                   | O(1)              |
| Binary Search  | O(log n)               | O(1)              |

Binary Search assumes the array is already sorted — run a sort first.

### Pathfinding

| Algorithm | Time complexity | Space complexity |
|-----------|-------------------|-------------------|
| BFS       | O(rows * cols)    | O(rows * cols)    |

## Tech stack

- Java 25
- JavaFX 25 (Controls, FXML)
- Maven

## Project structure

```
src/main/java/com/zhumagulorken/
├── Main.java                       # Application entry point
├── ui/
│   ├── MainController.java         # Sorting & searching tab: canvas rendering, controls, thread orchestration
│   └── PathfindingController.java  # Pathfinding tab: grid rendering, mouse input, thread orchestration
└── algorithms/
    ├── SortAlgorithm.java           # Common interface for sorting algorithms
    ├── SearchAlgorithm.java         # Common interface for searching algorithms
    ├── PathfindingAlgorithm.java    # Common interface for pathfinding algorithms
    ├── CellVisitor.java             # Callback reporting a visited (row, col) during pathfinding
    ├── BubbleSort.java
    ├── BucketSort.java
    ├── CountingSort.java
    ├── HeapSort.java
    ├── InsertionSort.java
    ├── MergeSort.java
    ├── QuickSort.java
    ├── RadixSort.java
    ├── SelectionSort.java
    ├── ShellSort.java
    ├── LinearSearch.java
    ├── BinarySearch.java
    └── BFSPathfinder.java

src/main/resources/fxml/
├── main_scene.fxml                 # TabPane shell that includes the two tabs below
├── sorting_scene.fxml              # Sorting & searching tab: toolbar + drawing canvas
└── pathfinding_scene.fxml          # Pathfinding tab: toolbar + grid canvas
```

## Getting started

### Prerequisites

- JDK 25
- Maven 3.9+

### Run

```bash
git clone https://github.com/Ork2004/AlgoVision.git
cd AlgoVision
mvn clean javafx:run
```

Alternatively, open the project in IntelliJ IDEA and run `Main.java` directly.

## Usage

### Sorting & Searching tab

1. Click **Generate** to create a new random array.
2. Pick a sorting algorithm from the dropdown and click **Start** to watch it sort.
3. To search, pick a search algorithm, enter a target value, and click **Search** — the currently probed bar is highlighted, then the found bar (or nothing, if not found) at the end. Binary Search requires the array to already be sorted.

### Pathfinding tab

1. Choose a cell mode (**Wall**, **Start**, **End**) and click cells on the grid to build a maze.
2. Click **Clear** to reset the grid.
3. Pick a pathfinding algorithm and click **Start** to watch it explore the grid and highlight the path it found.

## Roadmap

- Dijkstra and A* pathfinding
- Minimum spanning tree algorithms (Kruskal, Prim)
- Adjustable sorting speed via the existing slider
- Step counter and comparison/swap statistics

## Author

**Orken Zhumagul**
[Email](mailto:zhumagul.orken@gmail.com) | [GitHub](https://github.com/Ork2004)
