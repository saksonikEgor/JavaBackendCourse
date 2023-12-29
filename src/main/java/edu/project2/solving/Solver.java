package edu.project2.solving;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, Cell entrance, Cell exit);
}
