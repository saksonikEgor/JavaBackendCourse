package edu.project2.generation.dfs;

import edu.project2.generation.Generator;
import edu.project2.model.Edge;
import edu.project2.model.Maze;
import java.util.*;
import java.util.stream.IntStream;

public class DFSGenerator implements Generator {
    private final Random random;
    private int height;
    private int width;
    private final Deque<InitialCell> stack = new ArrayDeque<>();

    public DFSGenerator(Random random) {
        this.random = random;
    }

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);

        this.height = (height - 1) / 2;
        this.width = (width - 1) / 2;

        List<InitialCell> initialCells = createInitialCells();
        shuffleInitialCells(initialCells);

        maze.putSpanningTree(buildRandomSpanningTree(initialCells.getFirst()), this.width);
        return maze;
    }

    private List<InitialCell> createInitialCells() {
        List<InitialCell> initialCells = new ArrayList<>();

        IntStream.range(0, width * height).forEach(idx -> initialCells.add(new InitialCell(idx)));

        for (int i = 0; i < width * height - width; i++) {
            initialCells.get(i).addNeighbor(initialCells.get(i + width));

            if ((i + 1) % width != 0) {
                initialCells.get(i).addNeighbor(initialCells.get(i + 1));
            }
        }

        IntStream.range(width * height - width, width * height - 1).forEach(idx ->
            initialCells.get(idx).addNeighbor(initialCells.get(idx + 1))
        );

        return initialCells;
    }

    private void shuffleInitialCells(List<InitialCell> initialCells) {
        initialCells.forEach(cell -> cell.shuffle(random));
    }

    private List<Edge> buildRandomSpanningTree(InitialCell firstCell) {
        List<Edge> edges = new ArrayList<>();
        InitialCell cur = firstCell;

        stack.add(cur);
        cur.makeVisited();

        while (!stack.isEmpty()) {
            Optional<InitialCell> neighborOpt = cur.getRandomUnvisitedNeighbor();

            if (neighborOpt.isPresent()) {
                InitialCell neighbor = neighborOpt.get();
                neighbor.makeVisited();

                edges.add(new Edge(cur.getCellId(), neighbor.getCellId()));

                cur = neighbor;
                stack.push(cur);
            } else {
                cur = stack.poll();
            }
        }
        return edges;
    }

    static class InitialCell {
        private final int cellId;
        private final List<InitialCell> neighbors;
        private boolean visited = false;

        InitialCell(int cellId) {
            this.cellId = cellId;
            neighbors = new ArrayList<>();
        }

        void shuffle(Random random) {
            Collections.shuffle(neighbors, random);
        }

        void addNeighbor(InitialCell neighbor) {
            neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }

        Optional<InitialCell> getRandomUnvisitedNeighbor() {
            return neighbors.stream().filter(cell -> !cell.visited).findAny();
        }

        void makeVisited() {
            visited = true;
        }

        int getCellId() {
            return cellId;
        }
    }
}
