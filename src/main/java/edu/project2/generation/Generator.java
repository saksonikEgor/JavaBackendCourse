package edu.project2.generation;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.List;

public interface Generator {
    Maze generate(int height, int width);
}
