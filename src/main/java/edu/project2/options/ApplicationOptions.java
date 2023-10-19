package edu.project2.options;

import edu.project2.generation.Generator;
import edu.project2.generation.kruskal.KruskalGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.solving.Solver;
import edu.project2.solving.aStar.AStarSolver;
import java.util.List;
import java.util.Random;

public class ApplicationOptions {
    public enum GenerationAlgorithm {
        Kruskal;

        public Generator getGenerator(Random random) {
            return switch (this) {
                case Kruskal -> new KruskalGenerator(random);
            };
        }
    }

    public enum SolvingAlgorithm {
        AStar;

        public Solver getSolver() {
            return switch (this) {
                case AStar -> new AStarSolver();
            };
        }
    }

    private ApplicationOptions() {
    }
}
