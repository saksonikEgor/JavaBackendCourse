package edu.project2.solving.bfs;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.Node;
import edu.project2.properties.ApplicationProperties;
import edu.project2.solving.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultithreadedBFSSolver implements Solver {
    private final Queue<Node> queue;
    private final Set<Node> visited;
    private int height;
    private int width;
    private Node[][] grid;
    private Node start;
    private Node end;

    public MultithreadedBFSSolver() {
        queue = new ConcurrentLinkedDeque<>();
        visited = ConcurrentHashMap.newKeySet();
    }

    @Override
    public List<Cell> solve(Maze maze, Cell entrance, Cell exit) {
        Cell[][] mazeGrid = maze.getGrid();

        this.height = mazeGrid.length;
        this.width = mazeGrid[0].length;
        grid = Node.createGridOfNodes(mazeGrid);

        this.start = new Node(entrance.row(), entrance.column(), false);
        this.end = new Node(exit.row(), exit.column(), false);

        List<Cell> path;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            BFSSolvingTask task = new BFSSolvingTask(start, end);
            path = forkJoinPool.invoke(task).getFirst().reconstructPath();
        }

        return path;
    }

    private class BFSSolvingTask extends RecursiveTask<List<Node>> {
        private final Node cur;
        private final Node end;

        BFSSolvingTask(Node cur, Node end) {
            this.cur = cur;
            this.end = end;
        }

        @Override
        protected List<Node> compute() {
            List<Node> path = new ArrayList<>();

            visited.add(cur);
            if (cur.equals(end)) {
                path.add(cur);
                return path;
            }

            for (Node neighbor : getAllNeighbors(cur)) {
                neighbor.setParent(cur);
                visited.add(neighbor);

                BFSSolvingTask task = new BFSSolvingTask(neighbor, end);
                task.fork();
                path.addAll(task.join());
            }

            return path;
        }

//        private List<Cell> findPath() {
//            Node cur = start;
//            queue.add(cur);
//            visited.add(cur);
//
//            while (!queue.isEmpty()) {
//                cur = queue.poll();
//
//                if (cur.equals(end)) {
//                    return cur.reconstructPath();
//                }
//
//                for (Node neighbor : getAllNeighbors(cur)) {
//                    neighbor.setParent(cur);
//
//                    queue.add(neighbor);
//                    visited.add(neighbor);
//                }
//            }
//            return Collections.emptyList();
//        }

        private List<Node> getAllNeighbors(Node cur) {
            List<Node> neighbors = new ArrayList<>();

            for (int[] delta : ApplicationProperties.DELTAS) {
                int row = cur.getRow() + delta[0];
                int column = cur.getColumn() + delta[1];

                if (inBounds(row, column)) {
                    Node node = grid[row][column];

                    if (!node.isWall() && !visited.contains(node)) {
                        neighbors.add(node);
                    }
                }
            }
            return neighbors;
        }

        private boolean inBounds(int row, int column) {
            return row >= 0 && row < height
                && column >= 0 && column < width;
        }
    }
}
