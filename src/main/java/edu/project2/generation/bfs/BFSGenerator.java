package edu.project2.generation.bfs;

import edu.project2.generation.Generator;
import edu.project2.model.AlternatingCell;
import edu.project2.model.Edge;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

public class BFSGenerator implements Generator {
    private final Random random;
    private final Queue<AlternatingCell> queue;
    private int height;
    private int width;

    public BFSGenerator(Random random) {
        this.random = random;
        queue = new ArrayDeque<>();
    }

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);

        this.height = (height - 1) / 2;
        this.width = (width - 1) / 2;

        List<AlternatingCell> alternatingCells = createAlternatingCells();
        shuffleAlternatingCells(alternatingCells);

        maze.putSpanningTree(buildRandomSpanningTree(alternatingCells.getFirst()), this.width);
        return maze;
    }

    private List<AlternatingCell> createAlternatingCells() {
        List<AlternatingCell> alternatingCells = new ArrayList<>();

        IntStream.range(0, width * height).forEach(idx -> alternatingCells.add(new AlternatingCell(idx)));

        for (int i = 0; i < width * height - width; i++) {
            alternatingCells.get(i).addNeighbor(alternatingCells.get(i + width));

            if ((i + 1) % width != 0) {
                alternatingCells.get(i).addNeighbor(alternatingCells.get(i + 1));
            }
        }

        IntStream.range(width * height - width, width * height - 1).forEach(idx ->
            alternatingCells.get(idx).addNeighbor(alternatingCells.get(idx + 1))
        );

        return alternatingCells;
    }

    private void shuffleAlternatingCells(List<AlternatingCell> alternatingCells) {
        alternatingCells.forEach(cell -> cell.shuffle(random));
    }

    private List<Edge> buildRandomSpanningTree(AlternatingCell firstCell) {
        List<Edge> edges = new ArrayList<>();
        AlternatingCell cur = firstCell;

        queue.add(cur);
        cur.makeVisited();

        while (!queue.isEmpty()) {
            Optional<AlternatingCell> neighborOpt = cur.getRandomUnvisitedNeighbor();

            if (neighborOpt.isPresent()) {
                AlternatingCell neighbor = neighborOpt.get();
                neighbor.makeVisited();

                edges.add(new Edge(cur.getCellId(), neighbor.getCellId()));

                cur = neighbor;
                queue.add(cur);
            } else {
                cur = queue.poll();
            }
        }

//        stack.add(cur);
//        cur.makeVisited();
//
//        while (!stack.isEmpty()) {
//            Optional<AlternatingCell> neighborOpt = cur.getRandomUnvisitedNeighbor();
//
//            if (neighborOpt.isPresent()) {
//                AlternatingCell neighbor = neighborOpt.get();
//                neighbor.makeVisited();
//
//                edges.add(new Edge(cur.getCellId(), neighbor.getCellId()));
//
//                cur = neighbor;
//                stack.push(cur);
//            } else {
//                cur = stack.poll();
//            }
//        }
        return edges;
    }
}
