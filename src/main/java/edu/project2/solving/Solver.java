package edu.project2.solving;

import edu.project2.model.Cell;
import java.util.List;

public interface Solver {
    List<Cell> solve(Cell[][] grid, Cell start, Cell end);
}
