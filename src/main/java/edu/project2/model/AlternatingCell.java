package edu.project2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
}
