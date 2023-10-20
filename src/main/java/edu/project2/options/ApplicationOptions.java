package edu.project2.options;

import edu.project2.generation.Generator;
import edu.project2.generation.bfs.BFSGenerator;
import edu.project2.generation.dfs.DFSGenerator;
import edu.project2.generation.kruskal.KruskalGenerator;
import edu.project2.solving.Solver;
import edu.project2.solving.aStar.AStarSolver;
import edu.project2.solving.bfs.BFSSolver;
import edu.project2.solving.dfs.DFSSolver;
import java.util.Random;

public class ApplicationOptions {
    public static final String WALL_STRING = "██";
    public static final String PASSAGE_STRING = "  ";
    public static final String PATH_STRING = "▓▓";
    public static final String SELECT_A_MAZE_GENERATION_ALGORITHM_MESSAGE =
        "=== Choose a maze generation algorithm ===";
    public static final String SELECT_A_MAZE_SOLVING_ALGORITHM_MESSAGE = "=== Choose a maze solving algorithm ===";
    public static final String ENTER_COMMAND_TO_EXIT_MESSAGE = "0. Exit";
    public static final String SUGGESTION_TO_SELECT_A_MAZE_SIZE_MESSAGE =
        "=== Enter the size of the new maze in the [size] or [height width] format "
            + "for example: 8 12 ===";
    public static final String FAREWELL_MESSAGE = "Bye!";
    public static final String MAZE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Both the height and the width "
        + "of the maze must be at least 3";
    public static final String INVALID_MAZE_SIZE_MESSAGE = "Cannot generate a maze. Invalid size. Please try again";
    public static final String INVALID_NUMBER_OF_ALGORITHM_MESSAGE = "Incorrect option. Please try again";
    public static final int[][] DELTAS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private ApplicationOptions() {
    }

    public enum GenerationAlgorithm {
        Kruskal,
        DFS,
        BFS;

        public Generator getGenerator(Random random) {
            return switch (this) {
                case Kruskal -> new KruskalGenerator(random);
                case DFS -> new DFSGenerator(random);
                case BFS -> new BFSGenerator(random);
            };
        }
    }

    public enum SolvingAlgorithm {
        AStar,
        DFS,
        BFS;

        public Solver getSolver() {
            return switch (this) {
                case AStar -> new AStarSolver();
                case DFS -> new DFSSolver();
                case BFS -> new BFSSolver();
            };
        }
    }

}
