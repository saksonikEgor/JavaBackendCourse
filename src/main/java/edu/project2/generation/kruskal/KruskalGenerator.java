package edu.project2.generation.kruskal;

import edu.project2.generation.Generator;
import edu.project2.model.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static edu.project2.model.Cell.Type.PASSAGE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class KruskalGenerator implements Generator {
    private final int height;
    private final int width;
    private final Random random;

    public KruskalGenerator(int height, int width, Random random) {
        this.height = (height - 1) / 2;
        this.width = (width - 1) / 2;
        this.random = random;
    }

    @Override
    public List<Cell> generate(int height, int width) {
        List<Edge> edges = createEdges();
        Collections.shuffle(edges, random);

        return createPassages(buildRandomSpanningTree(edges));
    }

    private List<Edge> createEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int column = 1; column < width; column++) {
            edges.add(new Edge(
                toIndex(0, column),
                toIndex(0, column - 1)
            ));
        }

        for (int row = 1; row < height; row++) {
            edges.add(new Edge(
                toIndex(row, 0),
                toIndex(row - 1, 0)
            ));
        }

        for (int row = 1; row < height; row++) {
            for (int column = 1; column < width; column++) {
                edges.add(new Edge(
                    toIndex(row, column),
                    toIndex(row, column - 1)
                ));
                edges.add(new Edge(
                    toIndex(row, column),
                    toIndex(row - 1, column)
                ));
            }
        }
        return edges;
    }

    private int toIndex(int row, int column) {
        return row * width + column;
    }

    private List<Edge> buildRandomSpanningTree(List<Edge> edges) {
        DisjointSet disjointSets = new DisjointSet(width * height);

        return edges
            .stream()
            .filter(edge -> connects(edge, disjointSets))
            .collect(toList());
    }

    private boolean connects(Edge edge, DisjointSet disjointSet) {
        return disjointSet.union(edge.firstCell(), edge.secondCell());
    }

    private List<Cell> createPassages(List<Edge> spanningTree) {
        return spanningTree
            .stream()
            .map(edge -> getPassage(fromIndex(edge.firstCell()), fromIndex(edge.secondCell())))
            .collect(toList());
    }

    private Cell fromIndex(int index) {
        return new Cell(index / width, index % width, PASSAGE);
    }

    private Cell getPassage(Cell first, Cell second) {
        return new Cell(
            first.row() + second.row() + 1,
            first.column() + second.column() + 1,
            PASSAGE
        );
    }

    record Edge(int firstCell, int secondCell) {
    }

    static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            range(0, size).forEach(this::makeSet);
        }

        private void makeSet(int i) {
            parent[i] = i;
            rank[i] = 0;
        }

        public int find(int i) {
            if (i != parent[i]) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public boolean union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if (iRoot == jRoot) {
                return false;
            }

            if (rank[iRoot] < rank[jRoot]) {
                parent[iRoot] = jRoot;
            } else {
                parent[jRoot] = iRoot;
                if (rank[iRoot] == rank[jRoot]) {
                    rank[iRoot]++;
                }
            }
            return true;
        }
    }
}
