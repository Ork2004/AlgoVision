# AlgoVision

AlgoVision is a desktop application built with JavaFX that visualizes sorting algorithms in real time. Each algorithm runs on a background thread and renders the array as a bar chart on a canvas, redrawing after every swap so you can watch the sorting process step by step.

## Features

- Real-time visualization of sorting algorithms on a `Canvas`
- Randomized array generation with a single click
- Algorithm selection via a dropdown menu
- Non-blocking UI — sorting runs on a separate thread while the JavaFX render loop stays responsive
- Clean separation between algorithm logic and UI: every algorithm implements a common `SortAlgorithm` interface and reports progress through a callback, so the visualizer has no knowledge of how a given algorithm works internally

## Algorithms implemented

| Algorithm      | Time complexity (avg) | Space complexity |
|----------------|------------------------|-------------------|
| Bubble Sort    | O(n^2)                 | O(1)              |
| Insertion Sort | O(n^2)                 | O(1)              |
| Heap Sort      | O(n log n)             | O(1)              |

## Tech stack

- Java 25
- JavaFX 25 (Controls, FXML)
- Maven

## Project structure

```
src/main/java/com/zhumagulorken/
├── Main.java                     # Application entry point
├── ui/
│   └── MainController.java       # FXML controller: canvas rendering, controls, thread orchestration
└── algorithms/
    ├── SortAlgorithm.java         # Common interface for all algorithms
    ├── BubbleSort.java
    ├── InsertionSort.java
    └── HeapSort.java

src/main/resources/fxml/
└── main_scene.fxml                # Layout: toolbar with controls + drawing canvas
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

1. Click **Generate** to create a new random array.
2. Pick an algorithm from the dropdown.
3. Click **Start** to watch it sort.

## Roadmap

- Merge Sort, Quick Sort
- Graph algorithms (BFS, Dijkstra, Kruskal)
- Pathfinding visualization (A*, maze solver)
- Adjustable sorting speed via the existing slider
- Step counter and comparison/swap statistics

## Author

**Orken Zhumagul**
[Email](mailto:zhumagul.orken@gmail.com) | [GitHub](https://github.com/Ork2004)
