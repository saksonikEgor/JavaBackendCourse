package edu.project2.solving.dfs;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.Node;
import edu.project2.solving.Solver;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DFSSolver implements Solver {
    private static final int[][] DELTAS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private final Deque<Node> stack;
    private final Set<Node> visited;
    private int height;
    private int width;
    private Node[][] grid;
    private Node start;
    private Node end;

    public DFSSolver() {
        stack = new ArrayDeque<>();
        visited = new HashSet<>();
    }

    @Override
    public List<Cell> solve(Maze maze, Cell entrance, Cell exit) {
        Cell[][] mazeGrid = maze.getGrid();

        this.height = mazeGrid.length;
        this.width = mazeGrid[0].length;
        this.grid = new Node[height][width];

        this.start = new Node(entrance.row(), entrance.column(), false);
        this.end = new Node(exit.row(), exit.column(), false);

        createNodes(mazeGrid);
        return findPath();
    }

    private void createNodes(Cell[][] mazeGrid) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = new Node(i, j, mazeGrid[i][j].isWall());
                this.grid[i][j] = node;
            }
        }
    }

    private List<Cell> findPath() {
        Node cur = start;
        stack.push(cur);

        while (!stack.isEmpty()) {
            if (cur.equals(end)) {
                return reconstructPath(cur);
            }

            visited.add(cur);
            Optional<Node> neighborOpt = getFirstNeighbor(cur);

            if (neighborOpt.isPresent()) {
                Node neighbor = neighborOpt.get();
                neighbor.setParent(cur);
                cur = neighbor;

                stack.push(cur);
            } else {
                stack.poll();
                cur = stack.peek();
            }
        }
        return Collections.emptyList();
    }

    private List<Cell> reconstructPath(Node cur) {
        List<Cell> path = new LinkedList<>();
        Node node = cur;
        path.add(toCell(node));

        while (node.getParent() != node) {
            Node parent = node.getParent();

            path.addFirst(toCell(parent));
            node = parent;
        }
        return path;
    }

    private Cell toCell(Node node) {
        return new Cell(node.getRow(), node.getColumn(), Cell.Type.ESCAPE);
    }

    private Optional<Node> getFirstNeighbor(Node cur) {
        for (int[] delta : DELTAS) {
            int row = cur.getRow() + delta[0];
            int column = cur.getColumn() + delta[1];

            if (inBounds(row, column)) {
                Node node = grid[row][column];

                if (!node.isWall() && !visited.contains(node)) {
                    return Optional.of(node);
                }
            }
        }
        return Optional.empty();
    }

    private boolean inBounds(int row, int column) {
        return row >= 0 && row < height
            && column >= 0 && column < width;
    }
}
