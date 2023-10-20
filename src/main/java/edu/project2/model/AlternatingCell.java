package edu.project2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class AlternatingCell {
    private final int cellId;
    private final List<AlternatingCell> neighbors;
    private boolean visited = false;

    public AlternatingCell(int cellId) {
        this.cellId = cellId;
        neighbors = new ArrayList<>();
    }

    public void shuffle(Random random) {
        Collections.shuffle(neighbors, random);
    }

    public void addNeighbor(AlternatingCell neighbor) {
        neighbors.add(neighbor);
        neighbor.neighbors.add(this);
    }

    public Optional<AlternatingCell> getRandomUnvisitedNeighbor() {
        return neighbors.stream().filter(cell -> !cell.visited).findAny();
    }

    public void makeVisited() {
        visited = true;
    }

    public int getCellId() {
        return cellId;
    }

    public static List<AlternatingCell> createAlternatingCells(int width, int height) {
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

    public static void shuffleAlternatingCells(List<AlternatingCell> alternatingCells, Random random) {
        alternatingCells.forEach(cell -> cell.shuffle(random));
    }
}
